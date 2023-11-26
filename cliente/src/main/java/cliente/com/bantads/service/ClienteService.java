package cliente.com.bantads.service;

import java.util.List;

import cliente.com.bantads.model.Cliente;

public interface ClienteService {
	
	Cliente getById(Long id);
	Cliente salvarCliente(Cliente cliente);
	List<Cliente> listaCliente();
	void deletarCliente(Long id);
	boolean existeCliente(Long id);

}
