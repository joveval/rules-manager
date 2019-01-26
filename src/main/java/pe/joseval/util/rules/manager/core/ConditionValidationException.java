package pe.joseval.util.rules.manager.core;

public class ConditionValidationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConditionValidationException() {
		super();
	}

	public ConditionValidationException(String message) {
		super(message);
	}

	public ConditionValidationException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ConditionValidationException(Throwable throwable) {
		super(throwable);
	}
}
