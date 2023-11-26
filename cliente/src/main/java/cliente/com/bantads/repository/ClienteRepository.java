package cliente.com.bantads.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import cliente.com.bantads.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public List<Cliente> findAll();

}
