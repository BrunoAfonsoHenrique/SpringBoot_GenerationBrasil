/*Repository/DAO -Interface responsável pena comunicação direta com o banco de
dados. */

package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	/*FindAll --> Busca todos pelo Titulo;  By --> pelo
	 * Containing é a mesma cisa do Like do SQL
	 IgnoreCase é utilizado para não levar em consideração
	 maiusculo e minusculo --> deixa todos em minusculo */
}
