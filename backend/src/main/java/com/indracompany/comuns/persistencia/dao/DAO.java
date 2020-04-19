package com.indracompany.comuns.persistencia.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   19 de abr de 2020
 */
public abstract class DAO {

	@PersistenceContext
    protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

}