package com.adjt.acompanhamentoservice.entity;

import com.adjt.acompanhamentoservice.dto.generated.model.EventoTratamentoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tratamento_evento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TratamentoEvento {
    @Id
    @Column(nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamento_id")
    private Tratamento tratamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_medicao_id", nullable = false)
    private EventoMedicao eventoMedicao;

    @Column(name = "frequencia_horas", nullable = false)
    private Integer frequenciaHoras; // Ex: Medir a cada 8 horas

    public EventoTratamentoResponse toEventoDTO() {
        return new EventoTratamentoResponse().idTratamentoEvento(getId())
                .frequenciaHoras(getFrequenciaHoras())
                .evento(getEventoMedicao().toEventoDTO());
    }
}