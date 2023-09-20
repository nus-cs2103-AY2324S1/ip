package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Deadline;

/**
 * Represents the command that adds deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Deadline deadlineToAdd;

    public AddDeadlineCommand(String taskName, String deadline) throws DukeException {
        this.deadlineToAdd = new Deadline(taskName, deadline);
    }

    @Override
    public CommandResult execute() {
        assert deadlineToAdd != null: "deadline object should not be empty.";

        tasks.addTask(deadlineToAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
