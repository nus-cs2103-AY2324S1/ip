package duke.commands;

import duke.data.task.Task;

public class AddToDoCommand extends AddTaskCommand{

    public static final String COMMAND_WORD = "todo";

    public AddToDoCommand(Task task) {
        super(task);
    }
}