package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor//serve para instanciar o repositório, do ClienteRepository, 
				   //no caso uma boa prática, para que os métodos não fiquem 
                   //todos aqui na Controller. Ah nesse caso em específico estamos usando Lombok.
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;//AutoWired faz uso da Interface como se fosse uma classe.

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();//método que está implementado na interface ClienteRepository
		
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {//PathVariable (método para retornar apenas um cliente pelo ID) -- o método também poderia retornar apenas um cliente, mas nesse caso optou-se por retornar um ResponseEntity, passando um cliente, pois pode-se editar melhor as respostas que estarão relacionadas ao ResponseEntity que é usado para editar os nomes dos status
	return clienteRepository.findById(clienteId)
//			.map(cliente -> ResponseEntity.ok(cliente));
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
		
			//Podem ser utilizado dessa forma também!
	  		/*Optional<Cliente> cliente = clienteRepository.findById(clienteId);
				if (cliente.isPresent()) {
					return ResponseEntity.ok(cliente.get());
						}
						return ResponseEntity.notFound().build();*/
							}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {//esse método retorna o cliente que você acabou de passar por argumento
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
		    @RequestBody Cliente cliente){//metódo parecido com o método buscar 'busca um cliente pelo id para alterar'
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);//não cria um novo id e sim força usar o id retornado na busca
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
			clienteRepository.deleteById(clienteId);
			return ResponseEntity.noContent().build();
	}
	}
