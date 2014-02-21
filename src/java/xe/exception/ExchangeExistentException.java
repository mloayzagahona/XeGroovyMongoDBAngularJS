package xe.exception;

public class ExchangeExistentException extends XEException {

	private static final long serialVersionUID = 2903532752258472662L;

	public ExchangeExistentException(String message, String errorCode) {
		super(message, errorCode);
		this.errorCode = errorCode;
	}
}
