package br.newtonpaiva.lab.tcc.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.newtonpaiva.lab.tcc.common.exception.NotFoundException;
import br.newtonpaiva.lab.tcc.domain.entity.Livro;
import br.newtonpaiva.lab.tcc.domain.repo.LivroRespository;

@Service
public class LivroServiceImpl implements LivroService {
	
	@Autowired
	private LivroRespository repo;
	
	@Override
	public Livro getById(String id) {
		
		Optional<Livro> resultado = repo.findById(id);
		
		if (resultado.isEmpty()) {
			throw new NotFoundException(
					String.format("%s com ID [%s] não encontrado.", "Livro", id));
		}
		
		return resultado.get();
	}

	@Override
	public List<Livro> getLivros() {
		return repo.findAll();
	}

	@Override
	public Livro create(Livro livro) {
		
		livro.setId(UUID.randomUUID().toString());
		livro.setCreated(LocalDateTime.now());		
		livro.setModified(LocalDateTime.now());		

		repo.save(livro);
		
		return livro;
	}

	@Override
	public Livro update(String id, Livro livro) {

		Livro atual = getById(id);
		
		atual.setTitulo(livro.getTitulo());
		atual.setEditora(livro.getEditora());
		atual.setDataDePublicacao(livro.getDataDePublicacao());
		atual.setStatus(livro.getStatus());
		
		atual.setModified(LocalDateTime.now());
		
		repo.save(atual);
		
		return atual;
	}

	@Override
	public void deleteById(String id) {

		getById(id);

		repo.deleteById(id);
	}
}
