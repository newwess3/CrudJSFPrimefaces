/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newwess.agendaalunos.interfaces;

import com.newwess.agendaalunos.entities.Aluno;
import java.util.List;

/**
 *
 * @author Weslei
 */
public interface InterfaceAlunoJpa {
    
    void insere(Aluno aluno) ;
    void atualiza(Integer id) throws Exception;
    void atualiza(Aluno aluno);
    void deleta(Aluno aluno);
    void deleta(Integer id) throws Exception ;
    List lista();
    Aluno getAluno(Integer id);
    
}
