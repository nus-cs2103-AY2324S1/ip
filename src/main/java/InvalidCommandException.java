public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
    }

    public void print() {
        System.out.println("Invalid Command.\n" + Duke.horizontalLine);
    }
}