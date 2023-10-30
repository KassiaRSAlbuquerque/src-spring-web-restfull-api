package io.github.kassiarsalbuquerque.VendasSpringWeb.domain.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="produto")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(value = Include.NON_NULL)
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "descricao")
	@NotEmpty(message = "Campo descrição é obrigatório.")
	@Length(max = 10, message = "Campo descrição aceita até 10 caracteres.")
	private String descricao;
	
	@Column(name="preco_unitario")
	@NotNull(message = "Campo preço é obrigatório.")
	private Long preco;
	
	public Produto(String descricao, Long preco) {
		super();
		this.descricao = descricao;
		this.preco = preco;
	}

	public Produto() {
		super();
	}
}