package xe.exception;

public class ConversionMissingException extends XEException {

	private static final long serialVersionUID = -4778456455728374604L;

	public ConversionMissingException(String message, String errorCode) {
		super(message, errorCode);
		this.errorCode = errorCode;
	}

}
