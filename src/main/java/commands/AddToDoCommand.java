package commands;
import duke.DukeException;
import oop.Storage;
import oop.TaskList;
import oop.Ui;
import tasks.ToDo;

/**
 * The command which adds a TODO to the user's tasks upon execution.
 */
public class AddToDoCommand implements Command {
    /** The toDo to be added to the TaskList upon execution of this command.*/
    private ToDo toDo;

    /**
     * Constructs a command that adds the encapsulated toDo to the TaskList upon execution.
     * @param name The name of the toDo to be encapsulated in this command.
     */
    public AddToDoCommand(String name) {
        this.toDo = new ToDo(name, false);
        assert !this.toDo.isDone();
    }

    public ToDo getTodo() {
        return this.toDo;
    }

    /**
     * {@inheritDoc}
     * Executes the command to add the encapsulated toDo to the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (toDo.getName().length() == 0) {
            throw new DukeException("Empty Description");
        }
        tasks.addTask(this.toDo);
        return ui.getTaskAddedMessage(toDo, tasks);
    }
}
