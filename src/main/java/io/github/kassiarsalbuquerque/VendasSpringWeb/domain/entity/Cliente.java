package io.github.kassiarsalbuquerque.VendasSpringWeb.domain.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="cliente")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(value = Include.NON_NULL)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nome", length = 100)
	@NotEmpty(message = "{cliente-nome-not-empty}")
	private String nome;
	
	@Column(name = "cpf" , length = 11)
	@NotEmpty(message = "{cliente-cpf-not-empty}")
	@CPF(message = "{cliente-cpf-not-valid}")
	private String cpf;
	
	@Column(name = "usuario", length = 15)
	@NotEmpty(message = "{cliente-usuario-not-empty}")
	private String usuario;
	
	@Column(name = "senha", length = 80)
	@NotEmpty(message = "{cliente-senha-not-empty}")
	private String senha;
	
	@OneToMany(mappedBy = "cliente", targetEntity = Pedido.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Pedido> pedidos;
	
	public Cliente(String nome, String cpf, String usuario, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Cliente() {
		super();
	}
}