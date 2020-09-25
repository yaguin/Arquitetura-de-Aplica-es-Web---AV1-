package br.newtonpaiva.lab.tcc.api.livros.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.newtonpaiva.lab.tcc.common.enums.StatusDoLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
public class LivroRequest {
	
	private String id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String autor;

	@NotNull
	private String editora;
	
	@NotNull
	private LocalDateTime dataDePublicacao;
	
	@NotNull
	private StatusDoLivro status;
}
