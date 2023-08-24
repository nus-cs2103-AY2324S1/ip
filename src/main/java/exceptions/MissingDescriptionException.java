package exceptions;

public class MissingDescriptionException extends ThorndikeException {

    public MissingDescriptionException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }

}
