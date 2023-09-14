package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Event;

/**
 * Represents a command that adds event to the task list.
 */
public class AddEventCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Event eventToAdd;

    public AddEventCommand(String taskName, String from, String to) throws DukeException {
        this.eventToAdd = new Event(taskName, from, to);
    }

    @Override
    public CommandResult execute() {
        assert eventToAdd != null: "Event object should not be empty.";

        tasks.addTask(eventToAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
