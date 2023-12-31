package com.bantads.saga.DTO;

public class MensagemDTO {

    private String mensagem;
    private Object data;
    private boolean erro;

    
    public MensagemDTO() {
        this.erro = false;
    }

    public MensagemDTO(String mensagem, Object data, boolean erro) {
        this.mensagem = mensagem;
        this.data = data;
        this.erro = erro;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
 


