package org.generation.blogPessoal.model;

/* Model/Entity --> classe responsável pela abstração de nossos objetos e tabelas em
nossos banco de dados. */

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


/* Interpretar a classe com uma entidade */
@Entity
@Table(name = "Postagem")
public class Postagem {
	
	@Id /*Atributo vai virar uma Primary Key */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*Valor será gerado e seá do tipo identidade) */
	private long id;
	
	@NotNull /*Não pode vir nenhum titulo vazio */
	@Size(min = 5, max = 100) /* Deteminar a quantidade de caracteres */
	private String titulo;
	
	
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)/* Indica que estampos rabalhando com tempo */
	private Date date = new Date(System.currentTimeMillis()); /* Assim que passar algum dado pela classe, vai caputurar Hora, segundo e milesimo */
	
	
	//Relação 
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	
	/*Getter e Setters */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
}
