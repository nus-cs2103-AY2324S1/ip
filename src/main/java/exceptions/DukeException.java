package exceptions;

public class DukeException extends RuntimeException {
        public DukeException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
}
