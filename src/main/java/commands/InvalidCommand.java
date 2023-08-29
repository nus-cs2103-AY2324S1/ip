package commands;

public class InvalidCommand extends Command {

    private String prefix = "Invalid Command!";
    private String errMessage;

    public InvalidCommand(String errMessage) {
        this.errMessage = errMessage;
    }
    @Override
    public String execute() {
        return prefix + errMessage;
    }
}
