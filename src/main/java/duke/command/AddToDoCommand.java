package duke.command;

import duke.exception.DukeException;
import duke.task.ToDo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * The AddToDoCommand is the command for adding a ToDo to the task list.
 */
public class AddToDoCommand extends Command {
    private String description;

    /**
     * The constructor for an AddToDoCommand.
     *
     * @param description The description of the ToDo.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddToDoCommand.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws DukeException On input or file error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        ui.printAddSuccessMessage(todo, tasks.getAllTasks());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Checks whether it is an Exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
