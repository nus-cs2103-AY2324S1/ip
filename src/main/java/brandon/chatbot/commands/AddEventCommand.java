package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Event;

public class AddEventCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Event toAdd;

    public AddEventCommand(String taskName, String from, String to) throws DukeException {
        this.toAdd = new Event(taskName, from, to);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
