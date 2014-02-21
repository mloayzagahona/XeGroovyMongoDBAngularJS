package xe.exception;

public class XEException extends Exception {

	private static final long serialVersionUID = 4664456874499611218L;

	protected String errorCode = "Unknown_Exception";

	public XEException() {
	}

	public XEException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}
