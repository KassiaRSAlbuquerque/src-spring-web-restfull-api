package io.github.kassiarsalbuquerque.VendasSpringWeb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.github.kassiarsalbuquerque.VendasSpringWeb.domain.entity.Cliente;
import io.github.kassiarsalbuquerque.VendasSpringWeb.domain.repository.IClienteRepository;
import jakarta.validation.Valid;

@RestController
public class ClienteController {
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@RequestMapping(value = "/clientes", method = RequestMethod.POST)
	public ResponseEntity<Cliente> createCliente(@RequestBody @Valid Cliente cliente) {
		
		cliente.setSenha(BCrypt.withDefaults().hashToString(12, cliente.getSenha().toCharArray()));
		return new ResponseEntity<Cliente>(this.clienteRepository.save(cliente), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		
		Optional<Cliente> clienteConsultado = this.clienteRepository.findById(id);
		if (clienteConsultado.isPresent()) {
			cliente.setId(clienteConsultado.get().getId());
			
			if (cliente.getSenha()!= null)
				cliente.setSenha(BCrypt.withDefaults().hashToString(12, cliente.getSenha().toCharArray()));
				
			this.clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		} 
		
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deleteCliente(@PathVariable Integer id) {
		
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		
		if (cliente.isPresent()) {
			this.clienteRepository.deleteById(id);
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		} 
		
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> retriveCliente(@PathVariable Integer id) {
		
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		//ResponseEntity.ok(cliente.get()) <-- é igual a --> new ResponseEntity(cliente.get(), HttpStatus.OK);
		return cliente.isPresent()? ResponseEntity.ok(cliente.get()) : new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> retriveAllClientes(@RequestBody Cliente cliente) {
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(cliente, exampleMatcher);
		return ResponseEntity.ok(this.clienteRepository.findAll(example));
	}
}