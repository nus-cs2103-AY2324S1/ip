package cheems.exceptions;

public class EmptyArgumentException extends RuntimeException {
    public EmptyArgumentException(String keyword) {
        super(String.format("Please specify the correct number of arguments for keyword %s ~~", keyword));
    }
}
