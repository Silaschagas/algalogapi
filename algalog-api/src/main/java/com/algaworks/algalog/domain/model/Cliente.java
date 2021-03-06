package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity//representa uma entidade do nosso domínio. Nesse caso a classe possui o mesmo nome da tabela do banco de dados
//@Table(name = "tb_cliente") possui uma propriedade 'name' caso o nome da tabela no banco for diferente do nome da classe no java
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id//define a chave primária da aplicação
	@GeneratedValue(strategy = GenerationType.IDENTITY)//vai utilizar a forma nativa do banco de dados, ou seja vai utilizar o auto increment na geração de ID do banco de dados 
	private Long id;
	private String nome;
	private String email;
	
	@Column(name = "fone")
	private String telefone;

}
