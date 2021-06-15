/*Controller - “End point”, é a camada responsável por manipular todas as
requisições feitas do lado de fora da nossa API, essas requisições são feitas através
de URL’s seguindo o protocolo HTTP 
A camada de Controller é resposavel por --> Comunicação com o Client (Postiman, Angular etc) */

package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController /*Indica para o Spring que essa classe se trata de um controlador */
@RequestMapping("/postagens")/* Por qual RI essa classe é acessada */
@CrossOrigin("*")/*Quando o front começar a consumir essa API, independente da origem, ela tem que aceitar, ou seja, vai aceitar requisições de qualquer origem*/
public class PostagemController {
	
	
	
	
	@Autowired/*Injeção de dependencia (Inteface não instancia) --> Garantimos que todos os serviços do Interface Repository seja acessada a partir do Controller */
	private PostagemRepository repository;
	
	
	
	
	@GetMapping/*Expor para a API que esse método se trata de um Get */
	public ResponseEntity<List<Postagem>> GetAll(){ /*Retorna uma lista do tipo Postagem, nome do método é GetAll sem nada como parâmetro*/
		return ResponseEntity.ok(repository.findAll());
	}
	
	/*
	 *  INSERT INTO `db_revisao`.`postagem` (`date`, `texto`, `titulo`)
			VALUES("2021-05-30 10:10:10.000", "Estou aprendendo", "API REST Spring");

		SELECT * FROM db_revisao.postagem;
	 */
	
	
	
	@GetMapping("/{id}") /*Busca pot ID */
	public ResponseEntity<Postagem> GetById(@PathVariable long id){ /* O valor que vai entrar na variável id, vai vim como uma variável do caminho da URI @PathVariable */
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	/*Assim que for feito uma requisição do tipo Get em postagens / e passar um valor
	 * 	um atributo, no caso o id,	vai ser acessado diretamente esse método, a onde esse
	 * método irá capturar qual que é a variavel que  estamos recebendo dentro do PathVariable
	 * 
	 * @PathVariable --> Pegar o valor que vem da URL
	 * método repository.findById(id) --> retorna um optional
	 * método .map --> Se por acaso capturar alguma resposta positiva para ele pegar extamente o que tem dentro do optional 
	 * método orElse --> Dentro da colection do optional que vai retornar um notfound, caso não tenha nenhum tipo de reposta, caso venha nula do BD
	 * usuário pode consultar um id que não existe ae vem um notfound
	 * 
	 * 
		@PathVariable --> Quando queremos passar um valor pela URI (URL), recepcionamos o valor através de qual anotação
	 */
	}
	
	
	
	
	@GetMapping("/titulo/{titulo}") /*Find pelo titulo :: Outro caminho passado, adaptação para não gerar confusão*/
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){ /*@RequestBody --> Consegue pegar pela body, corpoda requisição */
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){ 
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id)  {
		repository.deleteById(id);
	}
	
	
}
