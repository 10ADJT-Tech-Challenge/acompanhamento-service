package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.EventosApi;
import com.adjt.acompanhamentoservice.dto.generated.model.Evento;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoRequest;
import com.adjt.acompanhamentoservice.entity.EventoMedicao;
import com.adjt.acompanhamentoservice.entity.UnidadeMedida;
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
        return null;
    }

    @Override
    public ResponseEntity<Evento> buscarEventoPorId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Evento> criarEvento(EventoRequest eventoRequest) {
        EventoMedicao evento = eventoMedicaoService.criar(new EventoMedicao(
                UUID.randomUUID(),
                eventoRequest.getNome(),
                UnidadeMedida.fromSimbolo(eventoRequest.getUnidadeMedida().getValue()),
                eventoRequest.getValorRefMax(),
                eventoRequest.getValorRefMin()
        ));

        return ResponseEntity.status(CREATED).body(toEventoDTO(evento));
    }

    @Override
    public ResponseEntity<Void> deletarEvento(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoMedicaoService.buscarTodos().stream()
                .map(this::toEventoDTO)
                .toList();

        return ResponseEntity.ok(eventos);

    }

    private Evento toEventoDTO(EventoMedicao eventoMedicao) {
        return new Evento()
                .id(eventoMedicao.getId())
                .nome(eventoMedicao.getNome())
                .valorRefMax(eventoMedicao.getReferenciaMaxima())
                .valorRefMin(eventoMedicao.getReferenciaMinima());
    }
}
