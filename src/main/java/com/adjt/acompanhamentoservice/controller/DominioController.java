package com.adjt.acompanhamentoservice.controller;

import com.adjt.acompanhamentoservice.dto.generated.DominioApi;
import com.adjt.acompanhamentoservice.dto.generated.model.CID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DominioController implements DominioApi {
    @Override
    public ResponseEntity<List<CID>> listarCids() {
        return null;
    }
}
