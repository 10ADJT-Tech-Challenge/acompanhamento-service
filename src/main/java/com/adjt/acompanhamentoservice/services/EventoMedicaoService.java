package com.adjt.acompanhamentoservice.services;

import com.adjt.acompanhamentoservice.entity.EventoMedicao;
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
    public EventoMedicao criar(EventoMedicao eventoMedicao) {
        return eventoMedicaoRepository.save(eventoMedicao);
    }

    @Transactional(readOnly = true)
    public List<EventoMedicao> buscarTodos() {
        return eventoMedicaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EventoMedicao buscarPorId(UUID id) {
        return eventoMedicaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EventoMedicao não encontrado com o id: " + id));
    }

    @Transactional
    public EventoMedicao atualizar(UUID id, EventoMedicao eventoMedicaoDetails) {
        EventoMedicao eventoMedicao = buscarPorId(id);

        eventoMedicao.setNome(eventoMedicaoDetails.getNome());
        eventoMedicao.setUnidadeMedida(eventoMedicaoDetails.getUnidadeMedida());
        eventoMedicao.setReferenciaMinima(eventoMedicaoDetails.getReferenciaMinima());
        eventoMedicao.setReferenciaMaxima(eventoMedicaoDetails.getReferenciaMaxima());

        return eventoMedicaoRepository.save(eventoMedicao);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!eventoMedicaoRepository.existsById(id)) {
            throw new EntityNotFoundException("EventoMedicao não encontrado com o id: " + id);
        }
        eventoMedicaoRepository.deleteById(id);
    }
}
