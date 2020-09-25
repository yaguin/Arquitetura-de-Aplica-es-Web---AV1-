package br.newtonpaiva.lab.tcc.domain.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.newtonpaiva.lab.tcc.domain.entity.Livro;

@Repository
public interface LivroRespository extends MongoRepository<Livro, String>{

	public List<Livro> findByAutor(String autor);
	
}
