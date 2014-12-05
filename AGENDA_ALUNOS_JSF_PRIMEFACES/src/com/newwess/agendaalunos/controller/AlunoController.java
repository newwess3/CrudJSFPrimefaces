/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newwess.agendaalunos.controller;

import com.newwess.agendaalunos.converter.AlunoConverter;
import com.newwess.agendaalunos.entities.Aluno;
import com.newwess.agendaalunos.interfaces.InterfaceAlunoController;
import com.newwess.agendaalunos.jpa.AlunoBean;
import com.newwess.agendaalunos.jpa.AlunoJpa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import jsf.util.JsfUtil;

/**
 *
 * @author Weslei
 */
@ManagedBean(value="alunoController")
public class AlunoController implements InterfaceAlunoController, NavigationPages {
	
    private Aluno alunoCurrent = null;
    private List<Aluno> alunoList = null;
    private List<Aluno> alunoListSelecao = null;    
    private AlunoConverter alunoConverter = null;
    private PageInfo pageInfo = null;
    
    private AlunoJpa alunoJpa = null;
    //private AlunoBean alunoBean = null;
    
    
    
    

    public AlunoController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        alunoJpa = (AlunoJpa) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "alunoJpa");
        
        //alunoBean = (AlunoBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "alunoBean");
        
        pageInfo = new PageInfo();
        alunoConverter = new AlunoConverter();
    }
    
    public String currentDate(){
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String s = sdf.format(new Date(System.currentTimeMillis()));
    	
    	System.out.println("TimeDATE: "+ s);
    	return s;
    }
    
    public PageInfo getPageInfo(){
//        if(pageInfo.getItemCount() == -1){
//            pageInfo.setItemCount(alunoJpa.getItemCountAluno());
//        }
    	
        pageInfo.setItemCount(alunoJpa.getItemCountAluno());
        return pageInfo;
    }
     
    public String criarNovo(){
        
        newInstanceAluno();
        alunoCurrent.setDataCadastro(currentDate());
        return CADASTRAR_PAGE;
    }
    
    public String currentTimeDate(){
    	Date date = new Date(System.currentTimeMillis());
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	return df.format(date);
    }
    
    public void addActionEvent(ActionEvent event){
    	addMessage("Gravado com sucesso!");
    }
    
    @Override
    public String inserir() {
        
        try {
            synchronized(this){
                alunoJpa.insere(alunoCurrent);
            }
        	
//        	alunoBean.addAluno(alunoCurrent);
            
            newInstanceAluno();            
            
            //JsfUtil.addSuccessMessage("Aluno inserido com sucesso!");
            addMessage("Gravado com sucesso!");
        } catch (Exception e) {
            //JsfUtil.ensureAddErrorMessage(e, "Ocorreu um erro ao gravar os dados");
            addMessage("Error: "+ e.getMessage());
            return null;
        }

        return CADASTRAR_PAGE;
    }

    @Override
    public String inserir(Aluno aluno) {
    	
        try {
//            synchronized(this){
                alunoJpa.insere(aluno);
//            }
            
//            alunoBean.addAluno(aluno);
            
            newInstanceAluno();
            //JsfUtil.addSuccessMessage("Aluno inserido com sucesso!");
            addMessage("Registrado com sucesso!");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "Ocorreu um erro ao gravar os dados");
            return null;
        }

        return CADASTRAR_PAGE;

    }
    
    public String moveToEditar(Aluno a){
    	setAluno(a);
    	
    	return ALTERAR_PAGE;
    }
    
    public String toEditar(){
        String strId = JsfUtil.getRequestParameter("paramEdita");
		String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("paramEdita");
        
        Integer id = Integer.valueOf(strId);
        try{
            alunoCurrent = alunoJpa.getAluno(id);
//            alunoCurrent = alunoBean.getAluno(id);
            
        }catch(Exception e){
            JsfUtil.ensureAddErrorMessage(e, e.getMessage());
            return ALTERAR_PAGE;
        }
        return ALTERAR_PAGE;
    }
    
    
    public String editar(Aluno a) {
        try{
            alunoJpa.atualiza(a);
//            alunoBean.updateAluno(a);
            
            newInstanceAluno();
            
            JsfUtil.addSuccessMessage("Dados salvos com sucesso!");
        }catch(Exception e){
            JsfUtil.ensureAddErrorMessage(e, e.getMessage());
            return null;
        }
        
        return ALTERAR_PAGE;
    }
    
    @Override
    public String editar() {
        try{
            alunoJpa.atualiza(alunoCurrent);        	
//        	alunoBean.updateAluno(alunoCurrent);
            
            newInstanceAluno();
            
            addMessage("Dados salvos com sucesso!");
            return ALTERAR_PAGE;
        }catch(Exception e){
            addMessage("Error: "+ e.getMessage());
            return ALTERAR_PAGE;
        }        
    }
    
    public String deleteSelected() {
        try{
        	Integer id = alunoCurrent.getId();
            alunoJpa.deleta(id);
//        	alunoBean.removeAluno(id);
        	
            addMessage("Removido com sucesso!");
            return LISTAR_PAGE;
        } catch (Exception e) {
            addMessage("Error: "+ e.getMessage());
            return LISTAR_PAGE;
        } 
    }    
    
    @Override
    public String deletar() {
        String strId = JsfUtil.getRequestParameter("paramRemove");
        //if(strId == null) return MAIN_PAGE;
        Integer id = Integer.valueOf(strId);
        try {
            alunoJpa.deleta(id);
//        	alunoBean.removeAluno(id);
        	
            JsfUtil.addSuccessMessage("Aluno deletado com sucesso!");
            
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, e.getMessage());
            return null;
        }
        return LISTAR_PAGE;
    }

    @Override
    public String deletar(Aluno aluno) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Aluno> getListaAlunos() {
    	//Used By AlunoJpa
        alunoList = alunoJpa.listaSelecao(pageInfo.getFirstItemCurrent(), pageInfo.getMaxItem());
    	
        
    	//Used By AlunoBean
//    	alunoList = alunoBean.getListSelection(pageInfo.getFirstItemCurrent(), pageInfo.getMaxItem());
    	
    	return alunoList;
    }
    
    public List<Aluno> getAllAlunos(){
    	alunoList = alunoJpa.lista();
    	return alunoList;
    }

    @Override
    public String procurar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Aluno getAluno() {
        if (alunoCurrent == null) {
            alunoCurrent = (Aluno) JsfUtil.getObjectFromRequestParameter("alunocurrent", alunoConverter, null);
        }
        if (alunoCurrent == null) {
            newInstanceAluno();
        }
        return alunoCurrent;
    }
    
    
    public void setAluno(Aluno aluno){
    	this.alunoCurrent = aluno;
    }

    
    private void newInstanceAluno(){
        alunoCurrent = new Aluno();
    }
    
    public List<Integer> getListaPaginas(){
        int tot = alunoJpa.lista().size();
        
        int res = tot%10;
        
        res = res>0?1:0;
                
        Integer pag = tot/10+res;
        
        List<Integer> l = new ArrayList<Integer>();
             
        for(int x=0; x<pag; x++,l.add(x));
 
        return l;
    }
    
//    public String paginaX(Integer pagina){
//        
////        FacesContext facesContext = FacesContext.getCurrentInstance();
//        
//        
//        final Integer maxResults = 10;
//        
//        int firstResult = pagina*maxResults - maxResults+1;
////        int end   = pagina*maxResults;
//        
//        alunoList = alunoJpa.selecaoAluno(firstResult, maxResults);
//        
////        return "listar_selecao"
//        return "listar";
//        
//    }
    
    public List<Aluno> getListaAlunosSelecao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        

//        if (alunoList == null) {
//            alunoList = (List<Aluno>) JsfUtil.getObjectFromRequestParameter("alunocurrent", alunoConverter, null);
//        }
//        if (alunoList == null) {
//            alunoList = new ArrayList<Aluno>();
//        }
        return alunoList;
    }
    
    public String nextPage(){
        getPageInfo().next();
        return LISTAR_PAGE;
    }
    
    public String prevPage(){
        getPageInfo().prev();
        return LISTAR_PAGE;
    }
    
	public String firstPage() {
		getPageInfo().first();
		return LISTAR_PAGE;
	}
    
    public String lastPage(){
        getPageInfo().last();
        return LISTAR_PAGE;
    }
    
    /**
     * Label de paginacao
     * @return
     */
    public String paginatorLabel(){
    	//Item #{alunoController.pageInfo.firstItemCurrent + 1}..#{alunoController.pageInfo.lastItemCurrent} de #{alunoController.pageInfo.itemCount}"
    	StringBuilder build = new StringBuilder();
    	build.append("Item ")
    	.append(pageInfo.getFirstItemCurrent() + 1)
    	.append("..")
    	.append(pageInfo.getLastItemCurrent())
    	.append(" de ")
    	.append(pageInfo.getItemCount());
    	
    	return build.toString();    	
    }
    
    public boolean paginatorLabelRendered(){
    	//alunoController.pageInfo.itemCount > alunoController.pageInfo.maxItem
    	return pageInfo.getItemCount() > pageInfo.getMaxItem();
    }
    
    public boolean firstPageRendered(){
    	//alunoController.pageInfo.lastItemCurrent > alunoController.pageInfo.maxItem
    	return pageInfo.getLastItemCurrent() > pageInfo.getMaxItem();
    }
    
    public boolean lastPageRendered(){
    	//alunoController.pageInfo.itemCount > alunoController.pageInfo.lastItemCurrent
    	return pageInfo.getItemCount() > pageInfo.getLastItemCurrent();
    }
    
    public boolean nextPageRendered(){
    	//alunoController.pageInfo.itemCount > alunoController.pageInfo.lastItemCurrent
    	return pageInfo.getItemCount() > pageInfo.getLastItemCurrent();
    }
    
    public boolean prevPageRendered(){
    	//alunoController.pageInfo.lastItemCurrent > alunoController.pageInfo.maxItem
    	return pageInfo.getLastItemCurrent() > pageInfo.getMaxItem();
    }
    
//    public void deletedUserData(){
//    	addMessage("Registro removido com sucesso!", "Continue");
//    }
    
    public void goToEditarPage(){
    	RequestContext.getCurrentInstance().execute("alterar");
    }
    
//    public void selectAlunoFromDatatable(Aluno a){
//    	RequestContext.getCurrentInstance().getcloseDialog(a);
//    }
    
    public String onAlunoChoosen(SelectEvent event) {
        alunoCurrent = (Aluno) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" + alunoCurrent.getId());
        
//        Integer id = a.getId();
//        alunoCurrent = alunoJpa.getAluno(id);
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        return ALTERAR_PAGE;
        
    }
    
    public String confirmDialogMessage(){
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirm Dialog OK"));
        return "";
    }
    
    public void deletedUserData(ActionEvent actionEvent){  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro removido com sucesso!",  "Continue");           
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
    
    public void updatedMessageAction(ActionEvent actionEvent){
    	addMessage("Registro atualizado com sucesso!");
    }
    
    public void deletedMessageAction(ActionEvent actionEvent) {
        addMessage("Registro removido com sucesso!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
     

    
}
