/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newwess.agendaalunos.controller;

/**
 *
 * @author Weslei
 */
import java.util.Locale;

import javax.annotation.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean(value="localeController")
public class LocaleController {

    private Locale currentLocale = new Locale("pt", "BR");

    public void enUsLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = Locale.US;
        viewRoot.setLocale(currentLocale);
    }

    public void ptBrLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("pt", "BR");
        viewRoot.setLocale(currentLocale);
    }

    
    public void esClLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("es", "CL");
        viewRoot.setLocale(currentLocale);
    }
    
    public Locale getCurrentLocale() {
        return currentLocale;
    }

}
