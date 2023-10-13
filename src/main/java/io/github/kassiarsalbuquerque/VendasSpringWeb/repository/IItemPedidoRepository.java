package io.github.kassiarsalbuquerque.VendasSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kassiarsalbuquerque.VendasSpringWeb.model.ItemPedido;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
