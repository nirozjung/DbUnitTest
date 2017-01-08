package com.nirozjungkarki;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PokemonDAOJPA implements PokemonDAO{
	
	private EntityManager entityManager;
	
	public PokemonDAOJPA(EntityManager entityManager){
		this.entityManager=entityManager;
	}
	
	public List<Pokemon> findByType(Type type){
		TypedQuery<Pokemon> typedQuery=entityManager.createNamedQuery(Pokemon.FIND_BY_TYPE, Pokemon.class);
        typedQuery.setParameter("ftype", type);
        return typedQuery.getResultList();
	}

	public boolean delete(Pokemon obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Pokemon> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pokemon getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pokemon insert(Pokemon obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(Pokemon obj) {
		// TODO Auto-generated method stub
		return false;
	}	
}
