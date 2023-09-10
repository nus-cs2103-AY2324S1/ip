package commands;

import functions.TaskList;
import tasks.Task;

public class MarkCommand extends Command {
    private TaskList taskList;
    private String[] inputArray;

    public MarkCommand(TaskList taskList, String[] inputArray) {
        this.taskList = taskList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() {
        try {
            int targetTaskIdx = Integer.parseInt(this.inputArray[1]) -1;
            Task task = this.taskList.get(targetTaskIdx);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.getTaskAsString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number provided. Please provide in the form of 'mark {task number}'. Eg: 'mark 1' to mark task 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided. Please provide in the form of 'mark {task number}'. Eg: 'mark 1' to mark task 1.");
        }
    }
}
