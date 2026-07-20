package com.adjt.acompanhamentoservice.services;

import com.adjt.acompanhamentoservice.dto.generated.model.Evento;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoRequest;
import com.adjt.acompanhamentoservice.entity.EventoMedicao;
import com.adjt.acompanhamentoservice.entity.UnidadeMedida;
import com.adjt.acompanhamentoservice.persistence.EventoMedicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoMedicaoService {
    private final EventoMedicaoRepository eventoMedicaoRepository;

    @Transactional
    public Evento criar(EventoRequest eventoRequest) {
        EventoMedicao eventoMedicao = EventoMedicao.toEntity(eventoRequest);
        return eventoMedicaoRepository.save(eventoMedicao).toEventoDTO();
    }

    @Transactional
    public Evento atualizar(UUID id, EventoRequest eventoRequest) {
        EventoMedicao eventoMedicao = buscarEntidadePorId(id);

        eventoMedicao.setNome(eventoRequest.getNome());
        eventoMedicao.setUnidadeMedida(UnidadeMedida.fromSimbolo(eventoRequest.getUnidadeMedida().getValue()));
        eventoMedicao.setReferenciaMinima(eventoRequest.getValorRefMax());
        eventoMedicao.setReferenciaMaxima(eventoRequest.getValorRefMin());

        return eventoMedicaoRepository.save(eventoMedicao).toEventoDTO();
    }

    @Transactional
    public void deletar(UUID id) {
        if (!eventoMedicaoRepository.existsById(id))
            throw new EntityNotFoundException("EventoMedicao não encontrado com o id: " + id);

        eventoMedicaoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Evento> buscarTodos() {
        return eventoMedicaoRepository.findAll().stream()
                .map(EventoMedicao::toEventoDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Evento buscarPorId(UUID id) {
        return buscarEntidadePorId(id).toEventoDTO();
    }

    public EventoMedicao buscarEntidadePorId(UUID id) {
        return eventoMedicaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EventoMedicao não encontrado com o id: " + id));
    }
}
