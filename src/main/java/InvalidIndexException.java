public class InvalidIndexException extends Exception {

    public InvalidIndexException() {
    }

    @Override
    public String toString() {

        String result = "\n\tOOPS! Please choose a proper index.";
        return Duke.dash + result + "\n" + Duke.dash;
    }
}
