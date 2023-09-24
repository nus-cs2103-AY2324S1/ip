package exceptions;

public class DukeException extends RuntimeException {

   /*
    * Constructs a DukeException object with the given message and error.
    *
    * @param errorMessage The error message.
    * @param err The throwable error.
    */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
