package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
import Tasks.ToDo;
public class AddToDoCommand implements Command {
    /** The toDo to be added to the TaskList upon execution of this command.*/
    private ToDo toDo;

    /**
     * Constructs a command that adds the encapsulated toDo to the TaskList upon execution.
     * @param name The name of the toDo to be encapsulated in this command.
     */
    public AddToDoCommand(String name) {
        this.toDo = new ToDo(name, false);
    }

    public ToDo getTodo() {
        return this.toDo;
    }

    /**
     * {@inheritDoc}
     * Executes the command to add the encapsulated toDo to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.toDo);
        ui.printTaskAddedMessage(toDo, tasks);
    }
}
