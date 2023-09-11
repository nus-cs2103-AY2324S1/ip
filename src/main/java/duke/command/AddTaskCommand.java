package duke.command;

import duke.data.storage.Store;
import duke.data.task.Task;
import duke.data.task.builder.Builder;
import duke.exception.DukeException;


public class AddTaskCommand implements Command {
    protected Builder<Task> taskBuilder;

    public AddTaskCommand (Builder<Task> taskBuilder) {
        this.taskBuilder = taskBuilder;
    }
    /**
     * Adds a task to the task list.
     * @param input String input from user that contains the task details.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(String input) throws DukeException {
        Store s = Store.getInstance();
        Task task = taskBuilder.buildFromString(input);
        if (task == null) {
            return "Invalid input!\n";
        }
        s.addTask(task);
        return "Got it. I’ve added this task:\n" + task + "\nNow you have " + s.getTaskCount() + " tasks in the list.\n";
    }

}
