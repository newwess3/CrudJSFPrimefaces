
package com.newwess.agendaalunos.converter;

import com.newwess.agendaalunos.entities.Aluno;
import com.newwess.agendaalunos.jpa.AlunoJpa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Weslei
 */
public class AlunoConverter implements Converter {
    
    /**
     * 
     * @param facesContext
     * @param component
     * @param string
     * @return 
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        AlunoJpa alunoJpa = (AlunoJpa) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "alunoJpa");
        return alunoJpa.getAluno(id);
    }

    /**
     * 
     * @param facesContext
     * @param component
     * @param object
     * @return 
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Aluno) {
            Aluno o = (Aluno) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Aluno");
        }
    }
    
}
