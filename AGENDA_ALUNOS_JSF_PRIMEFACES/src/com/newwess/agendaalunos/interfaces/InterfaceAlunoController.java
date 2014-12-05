/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.newwess.agendaalunos.interfaces;

import com.newwess.agendaalunos.entities.Aluno;
import java.util.List;

/**
 *
 * @author Weslei
 */
public interface InterfaceAlunoController {
    
    String inserir(Aluno aluno);
    String inserir();
    String editar();
    String deletar();
    String deletar(Aluno aluno);
    List<Aluno> getListaAlunos();
    String procurar(Integer id);
    
}
