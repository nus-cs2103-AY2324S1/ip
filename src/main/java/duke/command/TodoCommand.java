package duke.command;

import duke.Todo;
import duke.ui.TextUi;

/**
 * Represents a task that has to be done.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = "todo <description>";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "Sorry, but the description of a todo cannot be empty.";
    private final Todo taskToAdd;

    public TodoCommand(Todo task) {
        taskToAdd = task;
    }

    public String[] execute() {
        String[] response = new String[3];
        this.tasks.add(taskToAdd);

        response[0] = "Got it. I've added this task:";
        response[1] = String.format("%s%s", TextUi.INDENT, taskToAdd);
        response[2] = String.format("Now you have %d task%s in the list.",
                this.tasks.size(),
                this.tasks.size() == 1 ? "" : "s");
        return response;
    }
}
