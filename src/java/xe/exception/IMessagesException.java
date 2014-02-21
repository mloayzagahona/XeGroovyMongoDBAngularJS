package xe.exception;

public interface IMessagesException {

	String EXIST_COMBINATION_CODE = "101";
	String EXIST_COMBINATION_MSG  = "Already exists this combination.";

	String CONVERSION_ERROR_CODE = "102";
	String CONVERSION_ERROR_MSG  = "Problem conversion, the rate for this currency combination doesn't exist yet.";

}
