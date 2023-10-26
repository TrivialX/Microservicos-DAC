package clientews.com.bantads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List; 
import clientews.com.bantads.model.Cliente;
import clientews.com.bantads.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Override
    public Cliente getById(long id) {
        Cliente cliente = repo.getReferenceById(id);
        return cliente;
    }
    @Override
    public Cliente salvarCliente(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public List<Cliente> listaClientes() {
        return repo.findAll();
    }

    @Override
    public void deletarCliente(long id) {
            repo.deleteById(id);
    }
    @Override
    public boolean existeCliente(long id) {
        return repo.existsById(id);
    }
    

}
