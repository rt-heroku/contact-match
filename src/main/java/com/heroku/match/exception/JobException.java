package com.heroku.match.exception;

import java.util.ArrayList;
import java.util.List;

public class JobException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3596604560711578787L;
	private List<String> errorMessages = new ArrayList<>();
            
    public JobException() {
    }

    public JobException(Throwable t) {
    	super(t);
    }

    public JobException(String msg) {
        super(msg);
    }

    public JobException(String msg, Throwable t) {
    	super(msg, t);
    }
    
    /**
     * @return the errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
    }
}
