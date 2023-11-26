package cliente.com.bantads.model;
import java.io.Serializable;

public class ClienteDTO implements Serializable{
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String endereco;
	private String telefone;
	private Double salario;

	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Long id, String nome, String email, String cpf, String endereco, String telefone, Double salario) {
	    this.id = id;
	    this.nome = nome;
	    this.email = email;
	    this.cpf = cpf;
	    this.endereco = endereco;
	    this.telefone = telefone;
	    this.salario = salario;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
