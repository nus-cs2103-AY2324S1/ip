package commands;

import functions.TaskList;
import tasks.Task;

/**
 * The class for executing a delete command on a task from a TaskList
 */
public class DeleteCommand extends Command {

    private TaskList taskList;
    private String[] inputArray;

    /**
     * Constructs a new DeleteCommand object with the specified task list and input array.
     *
     * @param taskList The task list to delete the task from.
     * @param inputArray The input array containing the task number to be deleted.
     */
    public DeleteCommand(TaskList taskList, String[] inputArray) {
        this.taskList = taskList;
        this.inputArray = inputArray;
    }

    @Override
    public String execute() {
        try {
            int targetTaskIdx = Integer.parseInt(inputArray[1]) - 1;
            Task task = taskList.get(targetTaskIdx);
            taskList.remove(targetTaskIdx);
            String message = "Noted: I've removed this task: \n";
            message += task.getTaskAsString();
            return message;
        } catch (NumberFormatException e) {
            return "Invalid number provided. Please provide in the form of 'delete {task number}'. "
                    + "Eg: 'delete 1' to delete task 1.";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number provided. Please provide in the form of 'delete {task number}'. "
                    + "Eg: 'delete 1' to delete task 1.";
        } catch (Exception e) {
            return "Error in deleting task. Please write the command in the format 'delete {task number}' "
                    + "to delete task";
        }
    }
}

