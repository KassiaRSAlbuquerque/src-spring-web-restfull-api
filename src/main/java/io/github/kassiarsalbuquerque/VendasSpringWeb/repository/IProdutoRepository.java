package io.github.kassiarsalbuquerque.VendasSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kassiarsalbuquerque.VendasSpringWeb.model.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Integer> {

}
