package commands;

import tasks.Deadline;
import tasks.Event;

public class AddEventCommand extends Command{
    private Event toAdd;
    public static final String ADD_SUCCESS = "ok... I'm adding..";

    public AddEventCommand(String taskName, String from, String to) throws Exception {
        this.toAdd = new Event(taskName, from, to);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
