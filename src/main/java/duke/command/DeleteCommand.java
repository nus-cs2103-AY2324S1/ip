package duke.command;

import duke.tasks.Task;

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

    /**
     * Deletes the task with the given task number and returns the response for the user.
     *
     * @return the response to the user.
     */
    public String[] execute() {
        assert taskNumToDelete > 0 && taskNumToDelete <= this.duke.getTasks().size() : "Task number is not valid";
        String[] response = new String[3];

        try {
            Task task = this.duke.getTasks().get(taskNumToDelete);
            this.duke.getTasks().delete(taskNumToDelete);

            response[0] = "Noted. I've removed this task:";
            response[1] = task.toString();
            response[2] = String.format("Now you have %d task%s in the list.",
                    this.duke.getTasks().size(),
                    this.duke.getTasks().size() == 1 ? "" : "s");
            return response;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"Task number does not exist"};
        }
    }
}
