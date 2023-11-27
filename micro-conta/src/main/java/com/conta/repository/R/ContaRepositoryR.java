package com.conta.repository.R;
import com.conta.models.R.ContaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaRepositoryR extends JpaRepository<ContaR, Long> {
    @Query(value="SELECT cr.gerente_id  FROM tbl_conta cr GROUP BY cr.gerente_id ORDER BY COUNT(cr.id) asc limit 1", nativeQuery=true)
    Long countByGerenteId();
    @Query(value="SELECT * FROM tbl_conta WHERE id_cliente = ?1", nativeQuery=true)
    ContaR buscaContaPorIdCliente(Long id_cliente);

    List<ContaR> findByGerenteId(Long gerente_id);

    List<ContaR> findByGerenteIdAndSituacao(Long gerente_id, String situacao);


}
