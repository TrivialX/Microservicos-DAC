package clientews.com.bantads.repository;
import java.util.List;
import org.springframework.data.jpa.repository.*;

import clientews.com.bantads.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findAll();

}
