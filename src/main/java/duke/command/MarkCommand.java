package duke.command;

import duke.Task;
import duke.ui.TextUi;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = "mark <task number>";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "mark can only take an integer task number";
    private final int taskNumToMark;

    public MarkCommand(int taskNum) {
        taskNumToMark = taskNum;
    }

    public String[] execute() {
        try {
            String[] response = new String[2];
            Task task = this.tasks.get(taskNumToMark);

            task.mark();
            response[0] = "Nice! I've marked this task as done:";
            response[1] = String.format("%s%s", TextUi.INDENT, task);
            return response;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"Task number does not exist"};
        }
    }
}
