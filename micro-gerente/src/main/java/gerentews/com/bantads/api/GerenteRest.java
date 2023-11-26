package gerentews.com.bantads.api;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gerentews.com.bantads.model.Gerente;
import gerentews.com.bantads.model.GerenteDTO;
import gerentews.com.bantads.service.GerenteService;
import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class GerenteRest {
    @Autowired
    private GerenteService gerenteService;
    @Autowired
    private ModelMapper mapper;

    // @GetMapping("/gerentes")
    // public List<GerenteDTO> obterTodosGerentes() {
    // List<Gerente> lista = gerenteService.listaGerentes();
    // return lista.stream().map(gerente -> mapper.map(gerente,
    // GerenteDTO.class)).collect(Collectors.toList());

    // }
    @GetMapping("/gerentes")
    public ResponseEntity<List<GerenteDTO>> obterTodosGerentes() {
        try {
            List<Gerente> lista = gerenteService.listaGerentes();
            List<GerenteDTO> gerenteDTOs = lista.stream()
                    .map(gerente -> mapper.map(gerente, GerenteDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(gerenteDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/gerentes")
    public ResponseEntity<GerenteDTO> inserirGerente(@RequestBody GerenteDTO gerenteDTO) {
        try {
            mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            Gerente gerente = mapper.map(gerenteDTO, Gerente.class);
            Gerente novoGerente = gerenteService.salvarGerente(gerente);
            GerenteDTO novoGerenteDTO = mapper.map(novoGerente, GerenteDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoGerenteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/gerentes/{id}")
    public ResponseEntity<?> atualizarGerente(@PathVariable Long id, @RequestBody GerenteDTO gerenteDTO) {
        try {
            if (!gerenteService.existeGerente(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não foi possível atualizar, o gerente não foi encontrado.");
            }
            Gerente gerente = mapper.map(gerenteDTO, Gerente.class);
            gerente.setId(id);
            Gerente gerenteAtualizado = gerenteService.salvarGerente(gerente);
            GerenteDTO gerenteAtualizadoDTO = mapper.map(gerenteAtualizado, GerenteDTO.class);
            return ResponseEntity.ok(gerenteAtualizadoDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro de integridade de dados: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
        }
    }

    @DeleteMapping("/gerentes/{id}")
    public ResponseEntity<?> deletarGerente(@PathVariable Long id) {
        try {
            gerenteService.deletarGerente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
        }
    }

    @GetMapping("/gerentes/{id}")
    public ResponseEntity<GerenteDTO> obterGerentePorId(@PathVariable Long id) {
        try {
            Gerente gerente = gerenteService.getById(id);
            System.out.println(gerente);
            if (gerente == null) {
                throw new EntityNotFoundException("Gerente não encontrado para o ID: " + id);
            } else {
                GerenteDTO gerenteDTO = mapper.map(gerente, GerenteDTO.class);
                return ResponseEntity.ok(gerenteDTO);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gerente não encontrado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor." + e);
    }

}
