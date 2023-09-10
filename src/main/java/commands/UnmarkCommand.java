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
    public void execute() {
        try {
            int targetTaskIdx = Integer.parseInt(this.inputArray[1]) -1;
            Task task = this.taskList.get(targetTaskIdx);
            task.markAsUndone();
            System.out.println("Ok! I've marked this task as not done yet:");
            System.out.println(task.getTaskAsString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number provided. Please provide in the form of 'unmark {task number}'. Eg: 'unmark 1' to unmark task 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided. Please provide in the form of 'unmark {task number}'. Eg: 'unmark 1' to unmark task 1.");
        }
    }
}
