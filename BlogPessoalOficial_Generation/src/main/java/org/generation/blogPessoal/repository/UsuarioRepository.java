package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository --> Aqui que nós vamos construir a comunicação da nossa API com a nossa Base de dados.


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	
	public Optional<Usuario> findByUsuario(String usuario); // Procure pelo nome do atributo usuario
	
	List<Usuario> findAllByUsuarioContainingIgnoreCase(String usuario);

}
