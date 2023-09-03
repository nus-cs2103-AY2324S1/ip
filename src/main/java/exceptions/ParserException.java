package exceptions;


/**
 * The ParserException is thrown whenever there is some parsing error from the Parser object
 */
public class ParserException extends Exception {

    public ParserException() {
        super();
    }

    public ParserException(String message) {
        super(message);
    }

}
