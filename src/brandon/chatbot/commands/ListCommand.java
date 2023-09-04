package commands;

public class ListCommand extends Command {
    public static final String LIST_SUCCESS = "ok... I'm listing..";

    @Override
    public CommandResult execute() {

        return new CommandResult(LIST_SUCCESS, tasks);
    }
}
