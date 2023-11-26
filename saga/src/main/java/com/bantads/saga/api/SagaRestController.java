package com.bantads.saga.api;

import com.bantads.saga.DTO.AutoCadastroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.saga.DTO.ClienteDTO;
import com.bantads.saga.DTO.ContaDTO;
import com.bantads.saga.DTO.GerenteDTO;
import com.bantads.saga.service.AlteraGerenteService;
import com.bantads.saga.service.AlteraPerfilService;
import com.bantads.saga.service.AprovaCliente;
import com.bantads.saga.service.AutoCadastroService;
import com.bantads.saga.service.DeleteGerenteService;
import com.bantads.saga.service.InsereGerenteService;
import com.bantads.saga.service.ReprovaClienteService;

@RestController
@CrossOrigin
public class SagaRestController {

    @Autowired
    public AutoCadastroService autoCadService;
    @Autowired
    public InsereGerenteService insereGerService;
    @Autowired
    public DeleteGerenteService deleteGerService;
    @Autowired
    public AlteraGerenteService alteraGerService;
    @Autowired
    public AlteraPerfilService alteraPerService;
    @Autowired
    public AprovaCliente aprovaCliService;
    @Autowired
    public ReprovaClienteService reprovaCliService;


    @PostMapping("/registrar")
    public ResponseEntity<?> sagaAutocadastro(@RequestBody AutoCadastroDTO dto) {
    	autoCadService.initSagaAutoCadastro(dto);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
        
    @PostMapping("/gerentes")
    public ResponseEntity<?> sagaInsereGerente(@RequestBody GerenteDTO dto) {
        insereGerService.initSagaInsereGerente(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/gerentes/{id}")
    public ResponseEntity<?> sagaDeletarGerente(@PathVariable Long id) {
        try {
            deleteGerService.initDeleteGerente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("erro" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
            
        }
    }

    @PutMapping("/gerentes/{id}")
    public ResponseEntity<?> sagaAtualizarGerente(@PathVariable Long id, @RequestBody GerenteDTO gerenteDTO) {
        try {
            alteraGerService.initSagaAlteraGerente(gerenteDTO);
            return new ResponseEntity<>(HttpStatus.OK);
         } catch (Exception e) {
            System.out.println("erro" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
            
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> sagaAtualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            alteraPerService.initSagaAlteraPerfil(clienteDTO);
            return new ResponseEntity<>(HttpStatus.OK);
         } catch (Exception e) {
            System.out.println("erro" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
            
        }
    }

    @PutMapping("/clientes/aprovacao/{id}")
    public ResponseEntity<?> sagaAprovarCliente(@PathVariable Long id, @RequestBody ContaDTO contaDTO) {
        try {
            aprovaCliService.initSagaAprovaCliente(contaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
         } catch (Exception e) {
            System.out.println("erro" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
            
        }
    }

    @PutMapping("/clientes/reprovacao/{id}")
    public ResponseEntity<?> sagaReprovarCliente(@PathVariable Long id, @RequestBody ContaDTO contaDTO) {
        try {
            reprovaCliService.initSagaReprovaCliente(contaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
         } catch (Exception e) {
            System.out.println("erro" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
            
        }
    }


}
