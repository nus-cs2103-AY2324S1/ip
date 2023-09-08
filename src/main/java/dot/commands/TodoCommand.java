package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.Task;
import dot.tasks.TaskList;
import dot.tasks.Todo;

/**
 * Command to add a Deadline task to dotTaskList.
 */
public class TodoCommand extends Command {

    private final String description;

    private final TaskList dotTaskList;

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
        dotTaskList.addTask(newTodoTask, handleDotOutput);
        dotTaskList.saveTaskListToStorage();
    }

}
