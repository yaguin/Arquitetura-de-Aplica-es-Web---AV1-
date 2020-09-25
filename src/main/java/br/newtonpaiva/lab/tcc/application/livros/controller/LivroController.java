package br.newtonpaiva.lab.tcc.application.livros.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import br.newtonpaiva.lab.tcc.api.cursos.resource.CursosResource;
import br.newtonpaiva.lab.tcc.api.livros.request.LivroRequest;
import br.newtonpaiva.lab.tcc.api.livros.resource.LivrosResource;
import br.newtonpaiva.lab.tcc.api.livros.response.LivroResponse;
import br.newtonpaiva.lab.tcc.common.exception.NotFoundException;
import br.newtonpaiva.lab.tcc.domain.entity.Livro;
import br.newtonpaiva.lab.tcc.domain.service.LivroService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class LivroController implements LivrosResource {

	@Autowired
	private LivroService livroService;
	
	@Override
	public ResponseEntity<List<LivroResponse>> getAll() {
		log.info("GET livros");
		
		List<Livro> lista = livroService.getLivros();
		List<LivroResponse> response = new ArrayList<LivroResponse>();
		lista.forEach(livro -> {
			response.add(new LivroResponse(livro));
		});
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<LivroResponse> getById(Optional<String> id) {
		log.info("GET livro by ID: {}", id);

		Livro livro = livroService.getById(id.get());
		if (livro == null) {
			throw new NotFoundException(id.get());
		}
		
		return ResponseEntity.ok(new LivroResponse(livro));
	}

	@Override
	public ResponseEntity<LivroResponse> create(Optional<LivroRequest> request) {
		log.info("POST criar livro: {}", request);
		log.info(request.get().toString());

		Livro livro = new Livro()
				.withTitulo(request.get().getTitulo())
				.withId(UUID.randomUUID().toString())
				.withAutor(request.get().getAutor())
				.withEditora(request.get().getEditora())
				.withDataDePublicacao(request.get().getDataDePublicacao())
				.withStatus(request.get().getStatus());
		
		livro.setCreated(LocalDateTime.now());
		livro.setModified(LocalDateTime.now());
		livro = livroService.create(livro);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new LivroResponse(livro));
	}

	@Override
	public ResponseEntity<LivroResponse> update(
			Optional<String> id, 
			Optional<LivroRequest> request
			) {
		log.info("PUT atualizar livro: {} {}", id, request);

		Livro livro = new Livro()
				.withTitulo(request.get().getTitulo());
		livro = livroService.update(id.get(), livro);
		
		return ResponseEntity.status(HttpStatus.OK).body(new LivroResponse(livro));
	}

	@Override
	public ResponseEntity<Void> deleteById(Optional<String> id) {
		log.info("DELETE remover livro: {}", id);

		livroService.deleteById(id.get());
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
