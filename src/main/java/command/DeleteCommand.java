package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    protected int index;
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = " Noted. I've removed this task:\n";

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index >= 0 && this.index < tasks.getSize()) {
            Task task = tasks.getTask(this.index);
            tasks.deleteTask(task);
            storage.writeToFile(tasks.getList());
            ui.showMessage(MESSAGE_SUCCESS + "     " + task.toString()
                    + "\n Now you have " + tasks.getSize() + " tasks in the list");
        } else { // user input is an integer bigger than size of task list
            String message = tasks.isEmpty()
                    ? "You have no tasks! Please add some tasks first"
                    : "No such task! Please enter a task ID between 1 and " + tasks.getSize();
            throw new DukeException(message);
        }
    }
}
