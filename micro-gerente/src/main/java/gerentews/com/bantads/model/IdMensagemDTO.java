package gerentews.com.bantads.model;



public class IdMensagemDTO {
    private Long id;
    private String message;
    private boolean erro;
    
    public IdMensagemDTO(Long id, String message, boolean erro) {
        this.id = id;
        this.message = message;
        this.erro = erro;
    }

    public IdMensagemDTO() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isErro() {
        return erro;
    }
    public void setErro(boolean erro) {
        this.erro = erro;
    }
    
    
}
