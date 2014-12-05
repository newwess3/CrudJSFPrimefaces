package com.newwess.agendaalunos.entities;
// Generated 30/11/2011 15:16:26 by Hibernate Tools 3.2.1.GA

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

/**
 * @author Weslei
 */
@Entity
@Table(name = "aluno")
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a"), 
    @NamedQuery(name = "Aluno.findByAlunoId", query = "SELECT a FROM Aluno a WHERE a.id = :id")})
public class Aluno implements java.io.Serializable {
	//, @NamedQuery(name = "Aluno.findByAlunoSelecao", query = "SELECT * FROM aluno a LIMIT 11, 10")
    //, @NamedQuery(name = "Aluno.findByAlunoSelecao", query = "SELECT * FROM aluno a LIMIT :start, :nitens")
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6330219154826127952L;
	

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "data_cadastro")
    private String dataCadastro;
    @Column(name = "email")
    private String email;
    @Column(name = "nome")
    private String nome;
    @Column(name = "turma")
    private String turma;
    
    public Aluno() {
       
    }
    
    public Aluno(boolean clear){
        this("","","","");
    }

    public Aluno(String nome, String turma, String email, String dataCadastro) {
        this.nome = nome;
        this.turma = turma;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {  
    	this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", dataCadastro=" + dataCadastro + ", email=" + email + ", nome=" + nome + ", turma=" + turma + '}';
    }
    
    
}