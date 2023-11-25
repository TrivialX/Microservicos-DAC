package com.conta.repository.R;

import com.conta.models.R.MovimentacoesR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovimentacoesRepositoryR extends JpaRepository<MovimentacoesR, Long> {
    @Query(value = "SELECT * FROM tbl_movimentacoes mv WHERE mv.conta_id =?1 or mv.conta_destiny =?2", nativeQuery=true)
    List<MovimentacoesR> findByContaId(Long id, Long id_destiny);
}
