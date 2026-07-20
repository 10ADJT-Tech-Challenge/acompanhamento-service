package com.adjt.acompanhamentoservice.services;

import com.adjt.acompanhamentoservice.entity.Cid;
import com.adjt.acompanhamentoservice.persistence.CidRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CidService {

    private final CidRepository cidRepository;

    @Transactional(readOnly = true)
    public List<Cid> buscarTodos() {
        return cidRepository.findAll();
    }

    public Cid buscarPorId(@NotNull @Valid UUID idCid) {
        return cidRepository.findById(idCid)
                .orElseThrow(() -> new EntityNotFoundException("CID não encontrado com o id: " + idCid));
    }
}
