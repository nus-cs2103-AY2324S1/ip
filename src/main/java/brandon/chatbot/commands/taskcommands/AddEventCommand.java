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
    public static final String ADD_SUCCESS = "ok... I'm adding..-ㅅ-";
    private Event eventToAdd;

    public AddEventCommand(String taskName, String from, String to, Optional<ArrayList<Tag>> tags) throws DukeException {
        this.eventToAdd = new Event(taskName, from, to, tags);
    }

    @Override
    public CommandResult execute() {
        assert eventToAdd != null: "Event object should not be empty.";

        tasks.addTask(eventToAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
