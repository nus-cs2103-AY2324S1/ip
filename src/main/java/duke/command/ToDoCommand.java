package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents the Command ToDo that adds a ToDo to the list.
 *
 * @author Joseph Oliver Lim
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructs a ToDoCommand with a specified description.
     *
     * @param description A String describing the todo.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the ToDoCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(this.description);
        tasks.addTask(task);
        ui.printAddTask(task, tasks.getCountTasks());
    }
}
