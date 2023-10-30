package io.github.kassiarsalbuquerque.VendasSpringWeb.domain.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="item_pedido")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(value = Include.NON_NULL)
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	//@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	//@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Column
	private Integer qtdade;

	public ItemPedido(Pedido pedido, Produto produto, Integer qtdade) {
		super();
		this.pedido = pedido;
		this.produto = produto;
		this.qtdade = qtdade;
	}

	public ItemPedido() {
		super();
	}
}