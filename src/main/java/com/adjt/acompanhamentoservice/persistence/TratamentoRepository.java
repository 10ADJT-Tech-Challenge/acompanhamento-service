package com.adjt.acompanhamentoservice.persistence;

import com.adjt.acompanhamentoservice.entity.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TratamentoRepository extends JpaRepository<Tratamento, UUID> {
    List<Tratamento> findByCpfPaciente(String cpfPaciente);
}
