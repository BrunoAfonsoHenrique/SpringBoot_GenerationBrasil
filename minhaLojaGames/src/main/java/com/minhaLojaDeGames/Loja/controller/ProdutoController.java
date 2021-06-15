package com.minhaLojaDeGames.Loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.minhaLojaDeGames.Loja.model.Produto;
import com.minhaLojaDeGames.Loja.repository.ProdutoRepository;




@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repositoryP;
	
	@GetMapping("/todos")
	public  ResponseEntity<List<Produto>> GetAll(){
		List<Produto> listaDeProduto = repositoryP.findAll();
		
		if(listaDeProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(listaDeProduto);
		}	
	}
	
	
	@GetMapping("/{idProduto}")
	public ResponseEntity<Produto> GetById(@PathVariable long idProduto){
		return repositoryP.findById(idProduto).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricaoProduto")
	public ResponseEntity <Object> buscarDescricaoProduto(@RequestParam(defaultValue ="") String descricaoProduto){
		List<Produto> listaDeProduto = repositoryP.findAllByDescricaoProdutoContainingIgnoreCase(descricaoProduto);
				
		if(listaDeProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(listaDeProduto);
		}	
	}
	
	
	@PostMapping
	public ResponseEntity <Produto> post (@RequestBody Produto nomeProduto){
		return ResponseEntity.status(201).body(repositoryP.save(nomeProduto));
	}
	
	
	@PutMapping
	public ResponseEntity <Produto> put (@RequestBody Produto nomeProduto){
		return ResponseEntity.status(200).body(repositoryP.save(nomeProduto));
	}
	
	
	@DeleteMapping("/{idProduto}")
	public void delete(@PathVariable long idProduto ) {
		repositoryP.deleteById(idProduto);
	}

}
