public class InvalidCommandException extends Exception {

    String command;
    public InvalidCommandException(String command) {
        this.command = command;
    }
    @Override
    public String toString() {
        return String.format("Error: %s is an invalid command!", this.command);
    }

}
