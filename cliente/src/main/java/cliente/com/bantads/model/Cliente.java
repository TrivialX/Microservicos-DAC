package cliente.com.bantads.model;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {
	private static final Long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "endereco")
	private String endereco;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "salario")
	private Double salario;
	@Column(name = "id_gerente")
	private Long id_gerente;
	
	public Cliente() {
		
	}
	
	public Cliente(Long id, String nome, String email, String cpf, String endereco, String telefone, Double salario, Long id_gerente) {
	    this.id = id;
	    this.nome = nome;
	    this.email = email;
	    this.cpf = cpf;
	    this.endereco = endereco;
	    this.telefone = telefone;
	    this.salario = salario;
	    this.id_gerente = id_gerente;
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
	public Long getId_gerente() {
		return id_gerente;
	}
	public void setId_gerente(Long id_gerente) {
		this.id_gerente = id_gerente;
	}

}
