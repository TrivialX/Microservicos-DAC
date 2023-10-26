package clientews.com.bantads.service;

import java.util.List;

import clientews.com.bantads.model.Cliente;

public interface ClienteService {

    Cliente getById(long id);
    Cliente salvarCliente (Cliente cliente);
    List<Cliente> listaClientes();
    void deletarCliente(long id);
    boolean existeCliente(long id);
}
