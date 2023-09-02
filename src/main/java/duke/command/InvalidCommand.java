package duke.command;

public class InvalidCommand extends Command {

    public final String[] message;

    public InvalidCommand(String... message) {
        this.message = message;
    }

    public String[] execute() {
        return message;
    }
}
