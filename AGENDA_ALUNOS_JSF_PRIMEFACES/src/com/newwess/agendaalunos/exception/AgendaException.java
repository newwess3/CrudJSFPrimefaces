
package com.newwess.agendaalunos.exception;

/**
 *
 * @author Weslei
 */
public class AgendaException extends Exception  {
    
    public AgendaException( ) {
    }
    
    public AgendaException(String arg) {
        super(arg);
    }
    
    public AgendaException(Throwable arg) {
        super(arg);
    }
    
    public AgendaException(String arg, Throwable arg1) {
        super(arg, arg1);
    }
}
