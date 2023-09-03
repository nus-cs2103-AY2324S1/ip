package Ally.Commands;
import Ally.*;
import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Tasks.Todo;

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
    public void run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        try{
            Todo todo = new Todo(description);
            allyList.addElements(todo);
            storage.appendToFile(todo);
            allyList.printNewList(todo);
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
