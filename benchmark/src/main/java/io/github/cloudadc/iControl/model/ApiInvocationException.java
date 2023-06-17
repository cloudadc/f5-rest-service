package io.github.cloudadc.iControl.model;

public class ApiInvocationException extends RuntimeException {

	private static final long serialVersionUID = 702092185702463111L;


	public ApiInvocationException(Throwable cause) {
		super(cause);
	}

	public ApiInvocationException(String message) {
		super(message);
	}
}
