package com.bantads.saga.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.saga.DTO.ClienteAutoCadastroDTO;
import com.bantads.saga.service.AutoCadastroService;

@RestController
@CrossOrigin
public class SagaRestController {

    @Autowired
    public AutoCadastroService autoCadService;
    
    



    @PostMapping("/registrar")
    public ResponseEntity<?> sagaAutocadastro(@RequestBody ClienteAutoCadastroDTO dto){      
        autoCadService.initSagaAutoCadastro(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
