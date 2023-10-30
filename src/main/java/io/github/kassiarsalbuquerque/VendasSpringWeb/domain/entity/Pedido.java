package io.github.kassiarsalbuquerque.VendasSpringWeb.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.kassiarsalbuquerque.VendasSpringWeb.domain.enums.StatusPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="pedido")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(value = Include.NON_NULL)
public class Pedido implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "o cliente é obrigatório.")
	//@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;
	
	@Column(name = "total" , precision = 20 , scale = 2)
	private Long total;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido", targetEntity = ItemPedido.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ItemPedido> itens;
	
	public Pedido(Cliente cliente, Long total) {
		super();
		this.cliente = cliente;
		this.total = total;
	}

	public Pedido() {
		super();
	}
}