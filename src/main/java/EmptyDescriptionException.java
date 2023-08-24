public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {

        String result = "\n\tOOPS! The description of the " + getMessage() + " cannot be empty.";
        return Duke.dash + result + " \n" + Duke.dash;
    }
}
