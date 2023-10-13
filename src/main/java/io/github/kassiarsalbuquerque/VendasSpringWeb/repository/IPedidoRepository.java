package io.github.kassiarsalbuquerque.VendasSpringWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kassiarsalbuquerque.VendasSpringWeb.model.Cliente;
import io.github.kassiarsalbuquerque.VendasSpringWeb.model.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByCliente(Cliente cliente);
}
