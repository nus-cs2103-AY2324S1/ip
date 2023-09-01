public class DukeInvalidCommandException extends DukeException {

    String command;
    public DukeInvalidCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("Error: %s is an invalid command!", this.command);
    }

}
