package duke.command;

import duke.Task;
import duke.ui.TextUi;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "delete <task number>";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "delete can only take an integer task number";
    private final int taskNumToDelete;

    public DeleteCommand(int taskNum) {
        taskNumToDelete = taskNum;
    }

    public String[] execute() {
        String[] response = new String[3];

        try {
            Task task = this.tasks.get(taskNumToDelete);
            this.tasks.delete(taskNumToDelete);

            response[0] = "Noted. I've removed this task:";
            response[1] = String.format("%s%s", TextUi.INDENT, task);
            response[2] = String.format("Now you have %d task%s in the list.",
                    this.tasks.size(),
                    this.tasks.size() == 1 ? "" : "s");
            return response;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"Task number does not exist"};
        }
    }
}
