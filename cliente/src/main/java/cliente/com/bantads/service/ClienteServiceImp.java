package cliente.com.bantads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cliente.com.bantads.model.Cliente;
import cliente.com.bantads.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService{
    @Autowired
    private ClienteRepository repo;

    @Override
    public Cliente getById(Long id) {
        Cliente cliente = repo.getReferenceById(id);
        return cliente;
    }
    @Override
    public Cliente salvarCliente(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public List<Cliente> listaCliente() {
        return repo.findAll();
    }

    @Override
    public void deletarCliente(Long id) {
            repo.deleteById(id);
    }
    @Override
    public boolean existeCliente(Long id) {
        return repo.existsById(id);
    }

}
