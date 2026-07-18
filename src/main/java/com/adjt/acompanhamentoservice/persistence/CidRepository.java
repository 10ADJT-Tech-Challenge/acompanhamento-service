package com.adjt.acompanhamentoservice.persistence;

import com.adjt.acompanhamentoservice.entity.Cid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CidRepository extends JpaRepository<Cid, UUID> {
}
