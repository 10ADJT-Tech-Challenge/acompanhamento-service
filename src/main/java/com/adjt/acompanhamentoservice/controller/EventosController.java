package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.EventosApi;
import com.adjt.acompanhamentoservice.dto.generated.model.Evento;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class EventosController implements EventosApi {
    @Override
    public ResponseEntity<Evento> atualizarEvento(UUID id, EventoRequest eventoRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Evento> buscarEventoPorId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Evento> criarEvento(EventoRequest eventoRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletarEvento(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Evento>> listarEventos() {
        return null;
    }
}
