package duke.command;

import duke.Task;
import duke.ui.TextUi;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = "unmark <task number>";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "unmark can only take an integer task number";
    private final int taskNumToMark;

    public UnmarkCommand(int taskNum) {
        taskNumToMark = taskNum;
    }

    public String[] execute() {
        String[] response = new String[2];

        try {
            Task task = this.tasks.get(taskNumToMark);
            task.unmark();

            response[0] = "Ok, I've marked this task as not done yet:";
            response[1] = String.format("%s%s", TextUi.INDENT, task);
            return response;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"Task number does not exist"};
        }
    }
}
