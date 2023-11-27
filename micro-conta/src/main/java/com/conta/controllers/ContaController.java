package com.conta.controllers;


import com.conta.DTOS.*;
import com.conta.models.CUD.ContaCUD;
import com.conta.models.CUD.MovimentacoesCUD;
import com.conta.models.R.ContaR;
import com.conta.models.R.MovimentacoesR;
import com.conta.repository.CUD.MovimentacoesRepositoryCUD;
import com.conta.repository.R.MovimentacoesRepositoryR;
import com.conta.services.ContaService;
import com.conta.services.ContaServiceR;
import com.conta.services.MovimentacaoService;
import com.conta.services.MovimentacaoServiceR;
import org.hibernate.internal.log.SubSystemLogging;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ContaController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    ContaServiceR contaService;

    @Autowired
    ContaService contaServiceCud;

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    MovimentacaoServiceR movimentacoesServiceR;

    @Autowired
    MovimentacoesRepositoryR repoR;


    @Autowired
    MovimentacoesRepositoryCUD repoCUD;

    @GetMapping("/conta/{id}")
    ResponseEntity<ContaR> conta(@PathVariable Long id){
        ContaR conta = this.contaService.getContaById(id);
        if (conta != null) return ResponseEntity.status(200).body(conta);
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/conta/deposito/{id}")
    ResponseEntity<SaldoDTO> depositoController(@PathVariable Long id, @RequestBody DepositoDTO depositodto){
        if(depositodto.getValor() == null)return ResponseEntity.status(404).build();

        if(depositodto.getValor() > 0.0){
            ContaR conta = this.contaService.getContaById(id);
            Double saldo_anterior = conta.getSaldo();
            Double saldo_final = conta.getSaldo() + depositodto.getValor();
            conta.setSaldo(saldo_final);

            MovimentacoesCUD movimentacao = new MovimentacoesCUD();

            movimentacao.setTipo("DEPOSITO");
            movimentacao.setConta_id(conta.getId());
            movimentacao.setConta_destiny(null);
            movimentacao.setValue(depositodto.getValor());
            movimentacao.setSaldo_anterior_conta_id(saldo_anterior);
            movimentacao.setSaldo_final_conta_id(saldo_final);
            movimentacao.setDatahora(LocalDateTime.now());

            ContaCUD contaCUD = mapper.map(conta, ContaCUD.class);

            this.movimentacaoService.deposito(movimentacao);
            this.contaServiceCud.atualizaConta(contaCUD);
            SaldoDTO saldodto = new SaldoDTO();
            saldodto.setSaldo(saldo_final);
            return ResponseEntity.status(200).body(saldodto);

        }else{
            return ResponseEntity.status(404).build();
        }

    }

    @PostMapping("/conta/saque/{id}")
    ResponseEntity<SaldoDTO> saqueController(@PathVariable Long id, @RequestBody SaqueDTO saquedto){

        if(saquedto.getValor() > 0.0) {

            ContaR conta = this.contaService.getContaById(id);
            Double limite = conta.getLimite(); //se for 0 ou null

            if (conta.getLimite() == null || conta.getLimite() == 0){
                limite = 0.0;
            }

            if(conta.getSaldo() != null  && (conta.getSaldo() + limite) >= saquedto.getValor()){
                ContaCUD contaCUD = mapper.map(conta, ContaCUD.class);
                Double saldo_anterior = contaCUD.getSaldo();

                contaCUD.setSaldo(contaCUD.getSaldo() - saquedto.getValor());
                Double saldo_final = contaCUD.getSaldo();

                MovimentacoesCUD movimentacao = new MovimentacoesCUD();
                movimentacao.setTipo("SAQUE");
                movimentacao.setConta_id(contaCUD.getId());
                movimentacao.setConta_destiny(null);
                movimentacao.setValue(saquedto.getValor());
                movimentacao.setSaldo_anterior_conta_id(saldo_anterior);
                movimentacao.setSaldo_final_conta_id(saldo_final);
                movimentacao.setDatahora(LocalDateTime.now());

                this.movimentacaoService.saque(movimentacao);
                this.contaServiceCud.atualizaConta(contaCUD);
                SaldoDTO saldodto = new SaldoDTO();
                saldodto.setSaldo(saldo_final);
                return ResponseEntity.status(200).body(saldodto);
            }

            return ResponseEntity.status(404).build();

        }else{
            return ResponseEntity.status(404).build();
        }

    }


    @PostMapping("/conta/transferencia/{id}")
    ResponseEntity<SaldoDTO> transferenciaeController(@PathVariable Long id, @RequestBody TransferenciaDTO transferenciadto){

        if(transferenciadto.getValor() > 0){
            ContaR conta_origem = this.contaService.getContaById(id);
            ContaR conta_destino = this.contaService.getContaById(transferenciadto.getId_cliente());

            if(conta_destino == null){
                return ResponseEntity.status(404).build();
            }

            ContaCUD contaCUD_origem = mapper.map(conta_origem, ContaCUD.class);
            ContaCUD contaCUD_destino = mapper.map(conta_destino, ContaCUD.class);

            Double saldo_anterior_origem = contaCUD_origem.getSaldo();
            Double saldo_anterior_destino = contaCUD_destino.getSaldo();

            contaCUD_origem.setSaldo(saldo_anterior_origem - transferenciadto.getValor());
            contaCUD_destino.setSaldo(saldo_anterior_destino + transferenciadto.getValor());

            MovimentacoesCUD movimentacao = new MovimentacoesCUD();
            movimentacao.setTipo("TRANSFERENCIA");
            movimentacao.setConta_id(contaCUD_origem.getId());
            movimentacao.setConta_destiny(contaCUD_destino.getId());
            movimentacao.setValue(transferenciadto.getValor());
            movimentacao.setSaldo_anterior_conta_id(saldo_anterior_origem);
            movimentacao.setSaldo_anterior_conta_destiny(saldo_anterior_destino);
            movimentacao.setSaldo_final_conta_id(contaCUD_origem.getSaldo());
            movimentacao.setSaldo_final_conta_destiny(contaCUD_destino.getSaldo());
            movimentacao.setDatahora(LocalDateTime.now());
            this.movimentacaoService.transferencia(movimentacao);

            this.contaServiceCud.atualizaConta(contaCUD_origem);
            this.contaServiceCud.atualizaConta(contaCUD_destino);

            SaldoDTO saldodto = new SaldoDTO();
            saldodto.setSaldo(contaCUD_origem.getSaldo());
            return ResponseEntity.status(200).body(saldodto);

        }

        return ResponseEntity.status(404).build();
    }


    @GetMapping("/conta/extrato/{id}")
    ResponseEntity<List<ExtratoDTO>> extratoController(@PathVariable Long id){

        try{
            ContaR conta = this.contaService.getContaById(id);

            List<MovimentacoesR> movimentacoes = this.repoR.findByContaId(conta.getId(), conta.getId());
            List<ExtratoDTO> extrato = movimentacoes.stream()
                    .map(movi -> mapper.map(movi,ExtratoDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.status(200).body(extrato);

        }catch (Exception ex){
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/conta/teste")
    void teste(){
        Long id_gerente = 3L;
        List <ContaR> contas = this.contaService.buscaContasGerente(id_gerente);
        for (ContaR conta: contas) {
            System.out.println("ID_GERENTE CONTA_R:" + conta.getGerenteId());
        }
        List<ContaCUD> contasCUD = contas.stream().map(conta -> mapper.map(conta, ContaCUD.class)).collect(Collectors.toList());
        for (ContaCUD conta: contasCUD) {
            System.out.println("ID_GERENTE CONTA_cud:" + conta.getGerenteId());
        }

        Long gerMenosCont = this.contaService.buscaGerentesContas();
        System.out.println("GERENTE MENOS CONTAS: " + gerMenosCont);

        contaServiceCud.atualizarIdsDoGerente(contasCUD, gerMenosCont);

//        MessageListDTO messlist = new MessageListDTO();
//        messlist.setData(contasCUD);
//        this.sendMessageID("saga-conta-deletegerente-end", message);
//        this.sendMessageList("atualiza-conta-saga", messlist);


    }

    @GetMapping("/conta/gerente/{id}")
    ResponseEntity<List<ContaDTO>> contasGerente(@PathVariable Long id){
        try{
            List<ContaR> contas = this.contaService.buscaContasGerentePendente(id);

            List<ContaDTO> contaDTO = contas.stream().map(conta -> mapper.map(conta, ContaDTO.class)).collect(Collectors.toList());

            return ResponseEntity.status(200).body(contaDTO);


        }catch (Exception ex){
            return ResponseEntity.status(404).build();
        }
    }

}
