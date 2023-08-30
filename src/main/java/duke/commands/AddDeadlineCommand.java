package duke.commands;

import duke.data.task.Task;

public class AddDeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(Task task) {
        super(task);
    }
}