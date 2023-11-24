package com.bantads.saga.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.saga.DTO.ClienteAutoCadastroDTO;
import com.bantads.saga.DTO.InsereGerenteDTO;
import com.bantads.saga.service.AutoCadastroService;
import com.bantads.saga.service.DeleteGerenteService;
import com.bantads.saga.service.InsereGerenteService;

@RestController
@CrossOrigin
public class SagaRestController {

    @Autowired
    public AutoCadastroService autoCadService;
    @Autowired
    public InsereGerenteService insereGerService;
    @Autowired
    public DeleteGerenteService deleteGerService;

    @PostMapping("/registrar")
    public ResponseEntity<?> sagaAutocadastro(@RequestBody ClienteAutoCadastroDTO dto) {
        autoCadService.initSagaAutoCadastro(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/gerentes")
    public ResponseEntity<?> sagaInsereGerente(@RequestBody InsereGerenteDTO dto) {
        insereGerService.initSagaInsereGerente(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/gerentes/{id}")
    public ResponseEntity<?> deletarGerente(@PathVariable Long id) {
        try {
            deleteGerService.initDeleteGerente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
        }
    }

}
