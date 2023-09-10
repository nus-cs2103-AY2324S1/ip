package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.Task;
import dot.tasks.TaskList;
import dot.tasks.Todo;

/**
 * Command to add a Deadline task to dotTaskList.
 */
public class TodoCommand extends Command implements Undoable {

    private final String description;

    private final TaskList dotTaskList;

    /**
     * For the undo method.
     */
    private int oneBasedInsertIndex;

    /**
     * Constructor for TodoCommand.
     *
     * @param description This is the description of the Todo Task.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     */
    public TodoCommand(String description, TaskList dotTaskList) {
        assert !description.isEmpty() : "description is supposed to be a nonempty string";
        this.description = description;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Creates and inserts the Deadline into dotTaskList.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        Task newTodoTask = new Todo(this.description);
        oneBasedInsertIndex = dotTaskList.addTask(newTodoTask, handleDotOutput);
        dotTaskList.saveTaskListToStorage();
    }

    /**
     * Undo the creation of Todo.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void undo(Consumer<String> handleDotOutput) throws DotException {
        Command deleteCommand = new DeleteCommand(oneBasedInsertIndex, dotTaskList);
        deleteCommand.execute(handleDotOutput);
    }

    /**
     * {@inheritDoc}
     *
     * @return true to indicate that this is an Undoable Command.
     */
    @Override
    public boolean isUndoable() {
        return true;
    }

}
