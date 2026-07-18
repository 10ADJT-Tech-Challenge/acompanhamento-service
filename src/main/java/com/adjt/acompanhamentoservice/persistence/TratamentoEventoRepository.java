package com.adjt.acompanhamentoservice.persistence;

import com.adjt.acompanhamentoservice.entity.TratamentoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TratamentoEventoRepository extends JpaRepository<TratamentoEvento, UUID> {
}
