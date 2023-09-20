package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Event;

import java.util.ArrayList;
import java.util.Optional;

import static brandon.chatbot.commands.Feedback.ADD_SUCCESS;

/**
 * Represents a command that adds event to the task list.
 */
public class AddEventCommand extends Command {
    private Event eventToAdd;
    private String title;
    private String startingDate;
    private String endingDate;
    private Optional<ArrayList<Tag>> tags;

    public AddEventCommand(String title, String startingDate, String endingDate, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws DukeException {
        Event eventToAdd = new Event(title, startingDate, endingDate, tags);

        tasks.addTask(eventToAdd);

        if (tags.isPresent()) {
            addEventItemToMap(eventToAdd);
        }

        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }

    private void addEventItemToMap(Event eventToAdd) {
        for (Tag t : tags.get()) {
            tagTaskMap.add(t, eventToAdd);
        }
    }
}
