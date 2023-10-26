package clientews.com.bantads.api;

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

import clientews.com.bantads.model.Cliente;
import clientews.com.bantads.model.ClienteDTO;
import clientews.com.bantads.service.ClienteService;
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
public class ClienteRest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ModelMapper mapper;

    // @GetMapping("/clientes")
    // public List<ClienteDTO> obterTodosClientes() {
    // List<Cliente> lista = clienteService.listaClientes();
    // return lista.stream().map(cliente -> mapper.map(cliente,
    // ClienteDTO.class)).collect(Collectors.toList());

    // }
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        try {
            List<Cliente> lista = clienteService.listaClientes();
            List<ClienteDTO> clienteDTOs = lista.stream()
                    .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(clienteDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> inserirCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            Cliente cliente = mapper.map(clienteDTO, Cliente.class);
            Cliente novoCliente = clienteService.salvarCliente(cliente);
            ClienteDTO novoClienteDTO = mapper.map(novoCliente, ClienteDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoClienteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            if (!clienteService.existeCliente(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não foi possível atualizar, o cliente não foi encontrado.");
            }
            Cliente cliente = mapper.map(clienteDTO, Cliente.class);
            cliente.setId(id);
            Cliente clienteAtualizado = clienteService.salvarCliente(cliente);
            ClienteDTO clienteAtualizadoDTO = mapper.map(clienteAtualizado, ClienteDTO.class);
            return ResponseEntity.ok(clienteAtualizadoDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro de integridade de dados: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor.");
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> obterClientePorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.getById(id);
            System.out.println(cliente);
            if (cliente == null) {
                throw new EntityNotFoundException("Cliente não encontrado para o ID: " + id);
            } else {
                ClienteDTO clienteDTO = mapper.map(cliente, ClienteDTO.class);
                return ResponseEntity.ok(clienteDTO);
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no servidor." + e);
    }

}
