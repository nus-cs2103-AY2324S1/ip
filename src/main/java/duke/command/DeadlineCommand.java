package duke.command;

import duke.Deadline;
import duke.ui.TextUi;

/**
 * Adds a Deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "deadline <description> /by <date>";
    private final Deadline taskToAdd;

    public DeadlineCommand(Deadline task) {
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
