package duke.command;

import duke.tasks.Task;

/**
 * Unmarks a task as undone.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = "unmark <task number>";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "unmark can only take an integer task number";
    private final int taskNumToMark;

    public UnmarkCommand(int taskNum) {
        taskNumToMark = taskNum;
    }

    /**
     * Unmarks a task with the given task number.
     *
     * @return the response to the user
     */
    public String[] execute() {
        assert taskNumToMark >= 0 && taskNumToMark < tasks.size() : "Task number is not valid";
        String[] response = new String[2];

        try {
            Task task = this.tasks.get(taskNumToMark);
            task.unmark();

            response[0] = "Ok, I've marked this task as not done yet:";
            response[1] = task.toString();
            return response;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"Task number does not exist"};
        }
    }
}
