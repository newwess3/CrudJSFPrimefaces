package com.newwess.agendaalunos.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.annotation.ManagedBean;

import com.newwess.agendaalunos.beans.AlunoEJB;
import com.newwess.agendaalunos.entities.Aluno;

@ManagedBean (value="alunoBean")
//@SessionScoped
public class AlunoBean {

	@EJB
	AlunoEJB alunoEJB; 
	
	private List<Aluno> alunoList = new ArrayList<Aluno>();
	
	
	public void addAluno(Aluno aluno){
		alunoEJB.add(aluno);
	}
	
	public void updateAluno(Aluno aluno){
		alunoEJB.update(aluno);
	}
	
	public void removeAluno(Aluno aluno){
		alunoEJB.remove(aluno);
	}
	
	public void removeAluno(Integer id){
		alunoEJB.remove(id);
	}
	
	public Aluno getAluno(Integer id){
		return alunoEJB.findById(id);
	}

	public List<Aluno> getAlunosList() {
		alunoList = alunoEJB.findAll();
		return alunoList;
	}
	
	/**
	 * Retorna o uma lista por selecao de paginas.
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<Aluno> getListSelection(Integer firstResult, Integer maxResults){
		alunoList = alunoEJB.findBySelection(firstResult, maxResults);
		return alunoList;
    }
}