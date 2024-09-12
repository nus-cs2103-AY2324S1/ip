package jeo.command;

import jeo.storage.Storage;
import jeo.task.Task;
import jeo.task.TaskList;
import jeo.task.ToDo;
import jeo.ui.Ui;

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
        super(true);
        this.description = description;
    }

    /**
     * Executes the ToDoCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        tasks.addTask(task);
        return ui.addTask(task, tasks.getCountTasks());
    }
}
