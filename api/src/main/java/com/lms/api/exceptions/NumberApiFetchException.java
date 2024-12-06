package com.lms.api.exceptions;

import java.io.Serial;

public class NumberApiFetchException  extends RuntimeException{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	
	public NumberApiFetchException() {
        super("Invalid Score!");
    }

    public NumberApiFetchException(final String message) {
        super(message);
    } 

}