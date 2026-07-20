package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.TratamentosApi;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoTratamentoResponse;
import com.adjt.acompanhamentoservice.dto.generated.model.TratamentoRequest;
import com.adjt.acompanhamentoservice.dto.generated.model.TratamentoResponse;
import com.adjt.acompanhamentoservice.services.TratamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class TratamentosController implements TratamentosApi {

    private final TratamentoService tratamentoService;

    @Override
    public ResponseEntity<TratamentoResponse> criarTratamento(TratamentoRequest tratamentoRequest) {
        TratamentoResponse tratamento = tratamentoService.criar(tratamentoRequest);
        return ResponseEntity.status(CREATED).body(tratamento);
    }

    @Override
    public ResponseEntity<TratamentoResponse> atualizarTratamento(UUID id, TratamentoRequest tratamentoRequest) {
        TratamentoResponse tratamento = tratamentoService.atualizar(id, tratamentoRequest);
        return ResponseEntity.ok(tratamento);
    }

    @Override
    public ResponseEntity<TratamentoResponse> buscarTratamentoPorId(UUID id) {
        TratamentoResponse tratamento = tratamentoService.buscarPorId(id);
        return ResponseEntity.ok(tratamento);
    }

    @Override
    public ResponseEntity<Void> deletarTratamento(UUID id) {
        tratamentoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<TratamentoResponse>> getTratamentoByPaciente(String cpf) {
        List<TratamentoResponse> tratamentoResponses = tratamentoService.buscarTratamentosPorPaciente(cpf);
        return ResponseEntity.ok(tratamentoResponses);
    }

    @Override
    public ResponseEntity<EventoTratamentoResponse> getTratamentoEvento(UUID id) {
        EventoTratamentoResponse eventoTratamentoResponse = tratamentoService.buscarEventoTratamento(id);
        return ResponseEntity.ok(eventoTratamentoResponse);
    }

    @Override
    public ResponseEntity<List<TratamentoResponse>> listarTratamentos() {
        List<TratamentoResponse> tratamentos = tratamentoService.buscarTodos();
        return ResponseEntity.ok(tratamentos);
    }
}
