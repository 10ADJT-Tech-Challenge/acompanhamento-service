package com.adjt.acompanhamentoservice.persistence;

import com.adjt.acompanhamentoservice.entity.EventoMedicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoMedicaoRepository extends JpaRepository<EventoMedicao, UUID> {
}
