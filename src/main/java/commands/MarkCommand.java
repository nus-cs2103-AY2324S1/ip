package commands;

import functions.TaskList;
import tasks.Task;

/**
 * The class for a command to mark a task from a TaskList
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private String[] inputArray;

    /**
     * Constructs a new MarkCommand object with the specified task list and input array.
     *
     * @param taskList The task list to mark the task as done in.
     * @param inputArray The input array containing the task number to be marked as done.
     */
    public MarkCommand(TaskList taskList, String[] inputArray) {
        this.taskList = taskList;
        this.inputArray = inputArray;
    }

    @Override
    public String execute() {
        try {
            int targetTaskIdx = Integer.parseInt(this.inputArray[1]) - 1;
            Task task = this.taskList.get(targetTaskIdx);
            task.markAsDone();
            String message = "";
            message += "Nice! I've marked this task as done:\n";
            message += task.getTaskAsString();
            return message;
        } catch (NumberFormatException e) {
            return "Invalid number provided. Please provide in the form of 'mark {task number}'."
                    + " Eg: 'mark 1' to mark task 1.";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number provided. Please provide in the form of 'mark {task number}'."
                    + " Eg: 'mark 1' to mark task 1.";
        }
    }
}
