package ally.commands;
import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Todo;

/**
 * AddTodoCommand inherits from Commands.
 */
public class AddTodoCommand extends Commands {
    private final String description;

    /**
     * Constructor for AddTodoCommand.
     *
     * @param description
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new instance of Todo.
     * Adds the todo task to allyList.
     * Saves the task to the saved file.
     * Prints the todo task.
     *
     * @param allyList
     * @param ui
     * @param storage
     * @throws AllyException
     */
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        try {
            assert ui != null;
            assert storage != null;
            Todo todo = new Todo(description);
            allyList.addElements(todo);
            storage.appendToFile(todo);
            return allyList.printNewList(todo);
        } catch (AllyException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
