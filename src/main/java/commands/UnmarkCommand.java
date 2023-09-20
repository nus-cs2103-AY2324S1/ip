package commands;

import functions.TaskList;
import tasks.Task;

/**
 * The class for a command to unmark a task from a TaskList
 */
public class UnmarkCommand extends Command {

    private TaskList taskList;
    private String[] inputArray;

    /**
     * Constructs a new UnmarkCommand object with the specified task list and input array.
     *
     * @param taskList The task list to unmark the task as done in.
     * @param inputArray The input array containing the task number to be unmarked as done.
     */
    public UnmarkCommand(TaskList taskList, String[] inputArray) {
        this.taskList = taskList;
        this.inputArray = inputArray;
    }

    @Override
    public String execute() {
        try {
            int targetTaskIdx = Integer.parseInt(this.inputArray[1]) - 1;
            Task task = this.taskList.get(targetTaskIdx);
            task.markAsUndone();
            String message = "Ok! I've marked this task as not done yet: \n";
            message += task.getTaskAsString();
            return message;
        } catch (NumberFormatException e) {
            return "Invalid number provided. Please provide in the form of 'unmark {task number}'."
                    + " Eg: 'unmark 1' to unmark task 1.";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number provided. Please provide in the form of 'unmark {task number}'. "
                    + "Eg: 'unmark 1' to unmark task 1.";
        }
    }
}
