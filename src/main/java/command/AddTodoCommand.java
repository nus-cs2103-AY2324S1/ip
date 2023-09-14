package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;
import taskmanager.ToDo;

/**
 * Command to add a ToDos task.
 */
public class AddTodoCommand extends Command {
    private String taskDesc;

    /**
     * Constructs an `AddTodoCommand` object with the specified user input and due date string.
     *
     * @param taskDesc The task description.
     * @throws IllegalArgumentException If the provided task description is null.
     */
    public AddTodoCommand(String taskDesc) {
        assert taskDesc != null : "taskDesc must not be null";
        this.taskDesc = taskDesc;
    }


    /**
     * Executes the command to add a ToDos task to the task list, update the storage, and notify the user interface.
     *
     * @param task The task list to which the ToDos task will be added.
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks.
     *
     * @return     The string representation of the task.
     */
    @Override
    public String execute(TaskList task, Ui ui, FileHandler f) {
        ToDo newTodo = new ToDo(taskDesc);
        task.add(newTodo);
        f.writeTasksToFile(task);
        return "Help you added a new event.\n     " + newTodo.toString()
                + "\nNow you have " + task.size() + String.format(" task(s) in the list.");
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
