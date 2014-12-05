package com.newwess.agendaalunos.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import com.newwess.agendaalunos.entities.Aluno;

/**
 * Session Bean implementation class AlunoEJB
 */
@Stateless
@LocalBean
public class AlunoEJB {

	@PersistenceUnit(unitName = "AGENDA-ALUNOS-JSF-JSPPU")
    EntityManagerFactory emf;
	
	private List<Aluno> alunoList = new ArrayList<Aluno>();
	
	public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
	
	public void add(Aluno aluno){
		EntityManager em = getEntityManager();
		em.persist(aluno);
		em.close();
	}
	
	public void update(Aluno aluno){
		EntityManager em = getEntityManager();
		em.merge(aluno);
		em.close();
	}

	public void remove(Aluno aluno){
		EntityManager em = getEntityManager();
		em.remove(aluno);
		em.flush();
		em.close();
	}
	
	public void remove(Integer id){
		EntityManager em = getEntityManager();
		Aluno a = em.find(Aluno.class, id);
		
		em.remove(a);
		em.flush();
		em.close();
	}
	
	public Aluno findById(Integer id){
		EntityManager em = getEntityManager();
		return em.find(Aluno.class, id);
	}
	
	public List<Aluno> findAll() {
		EntityManager em = getEntityManager();
    	
    	TypedQuery<Aluno> query = em.createQuery("select a from Aluno a", Aluno.class);
    	alunoList = query.getResultList();
    	
    	return alunoList;    	
    }
	
	public List<Aluno> findBySelection(Integer firstResult, Integer maxResults){
		EntityManager em = getEntityManager();
		
		TypedQuery<Aluno> query = em.createQuery("select object(o) from Aluno as o", Aluno.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		alunoList = query.getResultList();
		return alunoList;
	}

}
