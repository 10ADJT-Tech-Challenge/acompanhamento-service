package com.adjt.acompanhamentoservice.entity;

import com.adjt.acompanhamentoservice.dto.generated.model.Evento;
import com.adjt.acompanhamentoservice.dto.generated.model.EventoRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "evento_medicao")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoMedicao {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(name = "referencia_minima")
    private Float referenciaMinima;

    @Column(name = "referencia_maxima")
    private Float referenciaMaxima;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EventoMedicao that = (EventoMedicao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Evento toEventoDTO() {
        return new Evento()
                .id(getId())
                .nome(getNome())
                .valorRefMax(getReferenciaMaxima())
                .valorRefMin(getReferenciaMinima());
    }

    public static EventoMedicao toEntity(EventoRequest eventoRequest) {
        return toEntity(UUID.randomUUID(), eventoRequest);
    }

    public static EventoMedicao toEntity(UUID id, EventoRequest eventoRequest) {
        return new EventoMedicao(
                id,
                eventoRequest.getNome(),
                UnidadeMedida.fromSimbolo(eventoRequest.getUnidadeMedida().getValue()),
                eventoRequest.getValorRefMax(),
                eventoRequest.getValorRefMin()
        );
    }
}