package commands;

import functions.TaskList;
import tasks.Task;

public class DeleteCommand extends Command {

    private TaskList taskList;
    private String[] inputArray;

    public DeleteCommand(TaskList taskList, String[] inputArray) {
        this.taskList = taskList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() {
        try {
            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
            Task task = taskList.get(targetTaskIdx);
            taskList.remove(targetTaskIdx);
            System.out.println("Noted: I've removed this task:");
            System.out.println(task.getTaskAsString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number provided. Please provide in the form of 'delete {task number}'. Eg: 'delete 1' to delete task 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided. Please provide in the form of 'delete {task number}'. Eg: 'delete 1' to delete task 1.");
        }
    }
}

