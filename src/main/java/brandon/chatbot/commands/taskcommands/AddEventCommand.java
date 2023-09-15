package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.tag.Tag;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Event;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents a command that adds event to the task list.
 */
public class AddEventCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Event toAdd;

    public AddEventCommand(String taskName, String from, String to, Optional<ArrayList<Tag>> tags) throws DukeException {
        this.toAdd = new Event(taskName, from, to, tags);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
