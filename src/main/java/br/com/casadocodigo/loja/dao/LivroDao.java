package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Livro livro) {
		manager.persist(livro);
	}
	
	public List<Livro> listar(){
		return manager.createQuery("select distinct(l) from Livro l join fetch l.autores", Livro.class).getResultList();
	}
	
	
}
