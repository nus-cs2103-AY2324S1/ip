package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.ToDo;

/**
 * Represents the command to add a todo task in the task list.
 */
public class AddToDoCommand extends Command {
    /** Description of the todo task. */
    private String description;

    /**
     * Constructor for the command.
     * 
     * @param description Description of the ToDo task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        assert (todo != null) : "todo not created";
        taskList.add(todo);
        ui.addToListSuccess(todo, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
