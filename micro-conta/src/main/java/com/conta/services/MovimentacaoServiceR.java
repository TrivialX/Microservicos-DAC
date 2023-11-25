package com.conta.services;

import com.conta.models.R.ContaR;
import com.conta.repository.R.MovimentacoesRepositoryR;
import com.conta.models.R.MovimentacoesR;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoServiceR {

    @Autowired
    private MovimentacoesRepositoryR movimentacaoRepoR;

    @Autowired
    private ModelMapper mapper;


    @Transactional("rTransactionManager")
    public void atualizaMovimentacao(MovimentacoesR movimentacao){

        try{
            this.movimentacaoRepoR.save(movimentacao);
            System.out.println("Movimentacao Conta Atualizada");
        }catch (Exception ex){
            System.out.println("Movimentacao deu pau ATT R");
        }

    }

//    @Transactional("rTransactionManager")
//    public List<MovimentacoesR> busca_extrato(ContaR conta){
//        return this.movimentacaoRepoR.extrato(conta.getId());
//    }

}
