package br.newtonpaiva.lab.tcc.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import br.newtonpaiva.lab.tcc.common.enums.RegimeDeCurso;
import br.newtonpaiva.lab.tcc.common.enums.StatusDoLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@Data
@With
@NoArgsConstructor
public class Livro {
	@Id
	private String id;
	
	private String titulo;
	
	private String autor;
	
	private String editora;
	
	private LocalDateTime dataDePublicacao;
	
	private StatusDoLivro status;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;

}
