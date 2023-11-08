package gerentews.com.bantads.repository;
import java.util.List;
import org.springframework.data.jpa.repository.*;

import gerentews.com.bantads.model.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    public List<Gerente> findAll();

}
