package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.EventosApi;
import com.adjt.acompanhamentoservice.dto.generated.model.Evento;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoRequest;
import com.adjt.acompanhamentoservice.services.EventoMedicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class EventosController implements EventosApi {

    private final EventoMedicaoService eventoMedicaoService;

    @Override
    public ResponseEntity<Evento> atualizarEvento(UUID id, EventoRequest eventoRequest) {
        Evento evento = eventoMedicaoService.atualizar(id, eventoRequest);
        return ResponseEntity.ok(evento);
    }

    @Override
    public ResponseEntity<Evento> buscarEventoPorId(UUID id) {
        Evento evento = eventoMedicaoService.buscarPorId(id);
        return ResponseEntity.ok(evento);
    }

    @Override
    public ResponseEntity<Evento> criarEvento(EventoRequest eventoRequest) {
        Evento evento = eventoMedicaoService.criar(eventoRequest);

        return ResponseEntity.status(CREATED).body(evento);
    }

    @Override
    public ResponseEntity<Void> deletarEvento(UUID id) {
        eventoMedicaoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoMedicaoService.buscarTodos();
        return ResponseEntity.ok(eventos);
    }
}
