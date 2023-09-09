package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Deadline;

/**
 * Represents the command that adds deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Deadline toAdd;

    public AddDeadlineCommand(String taskName, String deadline) throws DukeException {
        this.toAdd = new Deadline(taskName, deadline);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
