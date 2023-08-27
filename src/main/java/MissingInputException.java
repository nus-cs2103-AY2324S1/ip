public class MissingInputException extends Exception {
    public MissingInputException(String message) {
        super(message);
    }

    public void print() {
        System.out.println(this.getMessage() + "\n" + Duke.horizontalLine);
    }
}