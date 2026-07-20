package com.adjt.acompanhamentoservice.services;

import com.adjt.acompanhamentoservice.dto.generated.model.EventoTratamentoResponse;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoTratamentoSetup;
import com.adjt.acompanhamentoservice.dto.generated.model.TratamentoRequest;
import com.adjt.acompanhamentoservice.dto.generated.model.TratamentoResponse;
import com.adjt.acompanhamentoservice.entity.Cid;
import com.adjt.acompanhamentoservice.entity.EventoMedicao;
import com.adjt.acompanhamentoservice.entity.Tratamento;
import com.adjt.acompanhamentoservice.entity.TratamentoEvento;
import com.adjt.acompanhamentoservice.persistence.TratamentoEventoRepository;
import com.adjt.acompanhamentoservice.persistence.TratamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TratamentoService {
    private final TratamentoRepository tratamentoRepository;
    private final TratamentoEventoRepository tratamentoEventoRepository;
    private final CidService cidService;
    private final EventoMedicaoService eventoMedicaoService;

    @Transactional
    public TratamentoResponse criar(TratamentoRequest tratamentoRequest) {
        final Cid cid = cidService.buscarPorId(tratamentoRequest.getIdCid());
        final Tratamento tratamento = Tratamento.toEntity(tratamentoRequest, cid);
        tratamento.setEventos(criaTratamentoEventos(tratamentoRequest.getEventosConfigurados(), tratamento));

        return tratamentoRepository.save(tratamento).toTratamentoDTO();
    }

    @Transactional
    public TratamentoResponse atualizar(UUID id, TratamentoRequest tratamentoRequest) {
        Tratamento tratamento = buscarEntidadePorId(id);

        final Cid cid = cidService.buscarPorId(tratamentoRequest.getIdCid());

        tratamento.setCid(cid);
        tratamento.setDescricao(tratamentoRequest.getDescricaoMedico());
        tratamento.setCpfPaciente(tratamentoRequest.getCpfPaciente());
        tratamento.setCrmMedico(tratamentoRequest.getCrmMedico());
        tratamento.setDataFim(tratamentoRequest.getDataFinal());

        tratamento.getEventos().clear();
        tratamento.getEventos().addAll(criaTratamentoEventos(tratamentoRequest.getEventosConfigurados(), tratamento));

        return tratamentoRepository.save(tratamento).toTratamentoDTO();
    }

    @Transactional
    public void deletar(UUID id) {
        if (!tratamentoRepository.existsById(id))
            throw new EntityNotFoundException("Tratamento não encontrado com o id: " + id);

        tratamentoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TratamentoResponse> buscarTodos() {
        return tratamentoRepository.findAll().stream()
                .map(Tratamento::toTratamentoDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TratamentoResponse buscarPorId(UUID id) {
        return buscarEntidadePorId(id).toTratamentoDTO();
    }

    @Transactional(readOnly = true)
    public List<TratamentoResponse> buscarTratamentosPorPaciente(String cpf) {
        return  tratamentoRepository.findByCpfPaciente(cpf).stream()
                .map(Tratamento::toTratamentoDTO)
                .toList();
    }

    public EventoTratamentoResponse buscarEventoTratamento(UUID id) {
        return buscaTratamentoEventoEntidadePorId(id).toEventoDTO();
    }

    private TratamentoEvento buscaTratamentoEventoEntidadePorId(UUID id) {
        return tratamentoEventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TratamentoEvento não encontrado com o id: " + id));
    }

    private Tratamento buscarEntidadePorId(UUID id) {
        return tratamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tratamento não encontrado com o id: " + id));
    }

    private List<TratamentoEvento> criaTratamentoEventos(List<EventoTratamentoSetup> eventos, Tratamento tratamento) {
        return eventos.stream().map(evento -> {
            EventoMedicao eventoMedicao = eventoMedicaoService.buscarEntidadePorId(evento.getIdEvento());
            return new TratamentoEvento(UUID.randomUUID(), tratamento, eventoMedicao, evento.getFrequenciaHoras());
        }).toList();
    }
}
