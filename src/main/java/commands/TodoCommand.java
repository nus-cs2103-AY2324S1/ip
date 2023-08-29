package commands;

import errors.DotException;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

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
        this.description = description;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Creates and inserts the Deadline into dotTaskList.
     *
     * @throws DotException On detected error.
     */
    @Override
    public void execute() throws DotException {
        Task newTodoTask = new Todo(this.description);
        dotTaskList.addTask(newTodoTask);
        dotTaskList.saveTaskListToStorage();
    }
}
