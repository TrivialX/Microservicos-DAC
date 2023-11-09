package com.conta.services;

import com.conta.models.CUD.ContaCUD;
import com.conta.models.R.ContaR;
import com.conta.repository.CUD.ContaRepositoryCUD;
import com.conta.repository.R.ContaRepositoryR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepositoryCUD contaRepoCUD;
    @Transactional("cudTransactionManager")
    public void criarConta(ContaCUD conta){
        try{
            this.contaRepoCUD.save(conta);
            System.out.println("Conta salva");
        }catch (Exception ex){
            System.out.println("Conta deu pau CUD");
        }
    }
}
