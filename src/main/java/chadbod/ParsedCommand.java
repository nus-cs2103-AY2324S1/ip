package chadbod;

public class ParsedCommand {
    private final Command command;
    private final String details;

    public ParsedCommand(Command command, String details) {
        this.command = command;
        this.details = details;
    }

    public Command getCommand() {
        return this.command;
    }

    public String getDetails() {
        return this.details;
    }
}
