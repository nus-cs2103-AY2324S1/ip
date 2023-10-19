package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.ADD_SUCCESS;

import java.util.ArrayList;
import java.util.Optional;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Event;

/**
 * Represents a command that adds event to the task list.
 */
public class AddEventCommand extends Command {
    private String title;
    private String startingDate;
    private String endingDate;
    private Optional<ArrayList<Tag>> tags;

    /**
     * Constructs an AddEventCommand.
     * @param title of the event task to be added to the task list.
     * @param startingDate of the event task.
     * @param endingDate of the event task.
     * @param tags that are relevant to the event task. Tags are optional.
     */
    public AddEventCommand(String title, String startingDate, String endingDate, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws DukeException {
        // Creating a new event throws DukeException
        // when at least one of title, startingDate, or endingDate is a null.
        Event eventToAdd = new Event(title, startingDate, endingDate, tags);

        tasks.addTask(eventToAdd);

        if (tags.isPresent()) {
            addEventItemToMap(eventToAdd);
        }

        CommandResult result = new CommandResult(ADD_SUCCESS + "\n" + Message.showTasks(tasks));
        return result;
    }

    private void addEventItemToMap(Event eventToAdd) {
        for (Tag t : tags.get()) {
            tagTaskMap.add(t, eventToAdd);
        }
    }
}
