package com.bantads.saga.DTO;

public class AuthAutoCadastroDTO {
    private long id;
    private String email;
    private String tipo;

    public AuthAutoCadastroDTO() {
    }

    public AuthAutoCadastroDTO(long id, String email, String tipo) {
        this.id = id;
        this.email = email;
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    
}
