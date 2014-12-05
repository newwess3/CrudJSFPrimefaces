package com.newwess.agendaalunos.jpa;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.newwess.agendaalunos.entities.Aluno;
import com.newwess.agendaalunos.interfaces.InterfaceAlunoJpa;

/**
 *
 * @author Weslei
 */
//@ManagedBean(value="alunoJpa")
//@SessionScoped
public class AlunoJpa implements InterfaceAlunoJpa {

    @Resource
    private UserTransaction utx = null;
    
    @PersistenceUnit(unitName = "AGENDA-ALUNOS-JSF-JSPPU")
    private EntityManagerFactory emf = null;    
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AGENDA-ALUNOS-JSF-JSPPU");
    private EntityManager em;
    
    

    public EntityManager getEntityManager() {
    	if(emf == null)
    		emf = Persistence.createEntityManagerFactory("AGENDA-ALUNOS-JSF-JSPPU");
    	
    	
        return emf.createEntityManager();
    }

    @Override
    public void insere(Aluno aluno) {
        EntityManager em = getEntityManager();
        try {
            utx.begin();
            em.persist(aluno);         
            
            utx.commit();
        } catch (Exception e) {
        	
//            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public void atualiza(Integer id) {

        EntityManager em = getEntityManager();
        try {
            utx.begin();
            Aluno a = em.find(Aluno.class, id);
            em.merge(a);
            utx.commit();
        } catch (Exception e) {
//            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void atualiza(Aluno aluno) {

        EntityManager em = getEntityManager();
        try {
            utx.begin();
            em.merge(aluno);
            utx.commit();
        } catch (Exception e) {
//            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void deleta(Integer id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            utx.begin();
            Aluno a = em.find(Aluno.class, id);
            em.remove(a);
            utx.commit();
        } catch (Exception e) {
//            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void deleta(Aluno aluno) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List lista() {
        EntityManager em = getEntityManager();

        try {
            Query q = em.createNamedQuery("Aluno.findAll");
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Aluno getAluno(Integer id) {

        EntityManager em = getEntityManager();

        try {
            return em.find(Aluno.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }

//        EntityManager em = getEntityManager();
//        Aluno a = null;
//        try{
//            Query q = em.createNamedQuery("Aluno.findByAluniId").setParameter("id", id);
//            a = (Aluno) q.getSingleResult();
//            return a;
//        }catch(Exception e){
//            return null;
//        }finally{
//            if(em != null){
//                em.close();
//            }
//        }
    }

//    public List<Aluno> getTodosAlunos() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        
//        EntityManager em = getEntityManager();
//
//        try {
////            Query q = em.createQuery("select Object(o) from Aluno as o");
//            Query q = em.createNamedQuery("Aluno.findAll");
//            return q.getResultList();
//        } finally {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "Sucesso!",
//                    "");
//            context.addMessage(null, message);
//            if(em != null){
//                em.close();
//            }
//        }
//    }
    
    public int getItemCountAluno(){
        EntityManager em = getEntityManager();
        try{
//            Query q = em.createQuery("select count(*) from Aluno");
//            Query q = em.createQuery("select a from Aluno a");
//            return q.getMaxResults();
            Query q = em.createNamedQuery("Aluno.findAll");
            return q.getResultList().size();
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    
    public List<Aluno> selecaoAluno(Integer firstResult, Integer maxResults){
        EntityManager em = getEntityManager();
        
        try {
            
//            Query q = em.createNativeQuery("select*from aluno limit "+ start +","+ nitens);
//            Query q = em.createNamedQuery("Aluno.findByAlunoSelecao");
//            q.setParameter("start", start).setParameter("nitens", nitens);
            Query q = em.createQuery("Select a from Aluno a limit "+ firstResult +","+ maxResults);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }              
    }
    
    public List<Aluno> listaSelecao(Integer firstResult, Integer maxResults){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Aluno as o");
//            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
//            }
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
