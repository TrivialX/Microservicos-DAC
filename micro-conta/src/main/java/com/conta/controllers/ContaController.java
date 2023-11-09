package com.conta.controllers;


import com.conta.DTOS.DepositoDTO;
import com.conta.DTOS.SaldoDTO;
import com.conta.models.CUD.MovimentacoesCUD;
import com.conta.models.R.ContaR;
import com.conta.services.ContaService;
import com.conta.services.ContaServiceR;
import com.conta.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaServiceR contaService;

    @Autowired
    MovimentacaoService movimentacaoService;


    @GetMapping("/{id}")
    ResponseEntity<ContaR> conta(@PathVariable Long id){
        ContaR conta = this.contaService.getContaById(id);
        if (conta != null) return ResponseEntity.status(200).body(conta);
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/deposito/{id}")
    ResponseEntity<SaldoDTO> deposito(@PathVariable Long id, @RequestBody DepositoDTO deposito){
        if(deposito.getValor() > 0.0){
            ContaR conta = this.contaService.getContaById(id);
            conta.setSaldo(conta.getSaldo() + deposito.getValor());
            MovimentacoesCUD movimentacao = new MovimentacoesCUD();

            movimentacao.setType("DEPOSITO");
            movimentacao.setConta_id(conta.getId());
            movimentacao.setConta_destiny(null);
            movimentacao.setValue(deposito.getValor());

            //confirmar o formato de movimentação depois;

//            this.contaService.atualizaConta(conta);
        }else{
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(404).build();
    }


    @PostMapping("")
    void deposito(){

    }


}
