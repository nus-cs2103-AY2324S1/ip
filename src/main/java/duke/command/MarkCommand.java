package duke.command;

import duke.tasks.Task;

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

    /**
     * Marks the task with the given task number.
     *
     * @return the response to the user.
     */
    public String[] execute() {
        assert taskNumToMark >= 0 && taskNumToMark < tasks.size() : "Task number is not valid";

        try {
            String[] response = new String[2];
            Task task = this.tasks.get(taskNumToMark);

            task.mark();
            response[0] = "Nice! I've marked this task as done:";
            response[1] = task.toString();
            return response;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"Task number does not exist"};
        }
    }
}
