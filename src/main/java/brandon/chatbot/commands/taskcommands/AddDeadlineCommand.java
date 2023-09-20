package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.ADD_SUCCESS;

import java.util.ArrayList;
import java.util.Optional;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Deadline;

/**
 * Represents the command that adds deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String title;
    private String deadline;
    private Optional<ArrayList<Tag>> tags;

    /**
     * Constructs an AddDeadlineCommand with the given title, deadline, and tags of the deadline task.
     * @param title of the deadline task.
     * @param deadline of the deadline task.
     * @param tags that are relevant to the deadline task. Tags are optional.
     */
    public AddDeadlineCommand(String title, String deadline, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.deadline = deadline;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws DukeException {
        // Creating a deadline with one of title or deadline being null throws a DukeException.
        Deadline deadlineToAdd = new Deadline(title, deadline, tags);

        tasks.addTask(deadlineToAdd);

        if (tags.isPresent()) {
            addTodoItemToMap(deadlineToAdd);
        }

        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
    private void addTodoItemToMap(Deadline deadlineToAdd) {
        for (Tag t : tags.get()) {
            tagTaskMap.add(t, deadlineToAdd);
        }
    }
}
