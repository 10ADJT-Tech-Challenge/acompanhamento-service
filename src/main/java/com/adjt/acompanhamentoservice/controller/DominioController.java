package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.DominioApi;
import com.adjt.acompanhamentoservice.dto.generated.model.CID;
import com.adjt.acompanhamentoservice.entity.Cid;
import com.adjt.acompanhamentoservice.services.CidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DominioController implements DominioApi {
    private final CidService cidService;

    @Override
    public ResponseEntity<List<CID>> listarCids() {
        List<CID> cids = cidService.buscarTodos().stream()
                .map(this::toEventoDTO)
                .toList();

        return ResponseEntity.ok(cids);
    }

    private CID toEventoDTO(Cid cid) {
        return new CID()
                .id(cid.getId())
                .codigo(cid.getCodigo())
                .descricao(cid.getDescricao());
    }
}
