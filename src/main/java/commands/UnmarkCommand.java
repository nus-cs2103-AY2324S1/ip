package commands;

import functions.TaskList;
import tasks.Task;

public class UnmarkCommand extends Command{

    private TaskList taskList;
    private String[] inputArray;

    public UnmarkCommand(TaskList taskList, String[] inputArray) {
        this.taskList = taskList;
        this.inputArray = inputArray;
    }

    @Override
    public String execute() {
        try {
            int targetTaskIdx = Integer.parseInt(this.inputArray[1]) -1;
            Task task = this.taskList.get(targetTaskIdx);
            task.markAsUndone();
            String message = "Ok! I've marked this task as not done yet: \n";
            message += task.getTaskAsString();
            return message;
        } catch (NumberFormatException e) {
            return "Invalid number provided. Please provide in the form of 'unmark {task number}'. Eg: 'unmark 1' to unmark task 1.";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number provided. Please provide in the form of 'unmark {task number}'. Eg: 'unmark 1' to unmark task 1.";
        }
    }
}
