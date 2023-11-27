package com.conta.services;

import com.conta.models.R.ContaR;
import com.conta.repository.R.ContaRepositoryR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceR {

    @Autowired
    private ContaRepositoryR contaRepoR;

    @Transactional("rTransactionManager")
    public ContaR getContaById(Long id){
        Optional<ContaR> result = this.contaRepoR.findById(id);
        if(result.isPresent()) return result.get();
        return null;
    }

    @Transactional("rTransactionManager")
    public void atualizarConta(ContaR conta){

        try{
            this.contaRepoR.save(conta);
            System.out.println("Conta Atualizada");
        }catch (Exception ex){
            System.out.println("Conta deu pau R");
        }

    }

    @Transactional("rTransactionManager")
    public Long buscaGerentesContas(){
        try{
            return this.contaRepoR.countByGerenteId();
        }catch (Exception ex){
            System.out.println("Conta deu pau R");
        }

        return null;
    }

    @Transactional("rTransactionManager")
    public ContaR buscaContaPorIdCliente(Long id){
        try{
            return this.contaRepoR.buscaContaPorIdCliente(id);
        }catch (Exception ex){
            System.out.println("Conta deu pau R");
        }
        return null;
    }


    @Transactional("rTransactionManager")
    public List<ContaR> buscaContasGerentePendente(Long id){
        try{
            return this.contaRepoR.findByGerenteIdAndSituacao(id, "PENDENTE");
        }catch (Exception ex){
            System.out.println("Conta deu pau R");
        }

        return null;
    }


    @Transactional("rTransactionManager")
    public List<ContaR> buscaContasGerente(Long id){
        try{
            return this.contaRepoR.findByGerenteId(id);
        }catch (Exception ex){
            System.out.println("Conta deu pau R");
        }

        return null;
    }




}
