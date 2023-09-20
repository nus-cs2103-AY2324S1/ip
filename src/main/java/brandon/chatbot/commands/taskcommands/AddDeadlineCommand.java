package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Deadline;

import java.util.ArrayList;
import java.util.Optional;

import static brandon.chatbot.commands.Feedback.ADD_SUCCESS;

/**
 * Represents the command that adds deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String title;
    private String deadline;
    private Optional<ArrayList<Tag>> tags;

    public AddDeadlineCommand(String title, String deadline, Optional<ArrayList<Tag>> tags) {
        this.title = title;
        this.deadline = deadline;
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws DukeException {
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
