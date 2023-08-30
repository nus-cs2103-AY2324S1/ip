package command;

import data.storage.Store;
import data.task.Task;
import data.task.builder.Builder;
import exception.DukeException;

public class AddTask implements Command {
    protected Builder<Task> taskBuilder;

    public AddTask(Builder<Task> taskBuilder) {
        this.taskBuilder = taskBuilder;
    }
    /**
     * Adds a task to the task list.
     * @param input String input from user that contains the task details.
     * @throws DukeException If input is invalid.
     */
    @Override
    public void execute(String input) throws DukeException {
        Store s = Store.getInstance();
        Task task = taskBuilder.buildFromString(input);
        if (task == null) {
            System.out.println("Invalid input");
            return;
        }
        s.addTask(task);
        System.out.println("Got it. I’ve added this task:");
        System.out.println(task);
        System.out.println("Now you have " + s.getTaskCount() + " tasks in the list.");
    }

}
