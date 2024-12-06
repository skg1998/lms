package com.lms.api.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
        super("Resource Not found!");
    }

    public NotFoundException(final String message) {
        super(message);
    } 

}
