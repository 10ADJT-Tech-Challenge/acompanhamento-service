package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.TratamentosApi;
import com.adjt.acompanhamentoservice.dto.generated.model.TratamentoRequest;
import com.adjt.acompanhamentoservice.dto.generated.model.TratamentoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TratamentosController implements TratamentosApi {
    @Override
    public ResponseEntity<TratamentoResponse> atualizarTratamento(UUID id, TratamentoRequest tratamentoRequest) {
        return null;
    }

    @Override
    public ResponseEntity<TratamentoResponse> buscarTratamentoPorId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<TratamentoResponse> criarTratamento(TratamentoRequest tratamentoRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletarTratamento(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<TratamentoResponse> getTratamentoByPaciente(String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<List<TratamentoResponse>> listarTratamentos() {
        return null;
    }
}
