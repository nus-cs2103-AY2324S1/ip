package bellcurvegod.command;

import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the DeleteTaskCommand.
 */
public class DeleteTaskCommand {
    /**
     * Deletes the task(s).
     * @param taskParams task(s) to be deleted.
     * @return message after deleting a task.
     */
    public static String run(Task... taskParams) {
        return TaskList.delete(taskParams);
    }

    public static String getHelpMessage() {
        return "Type 'delete <index>' and enter, the app will delete the task with the given index.\n"
            + "You can also delete multiple tasks by typing 'delete <index>,<index>,<index>,...' "
            + "with no spaces between the indices and commas.";
    }
}
