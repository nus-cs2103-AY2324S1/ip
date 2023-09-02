package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.ToDos;

/**
 * Command to add a ToDos task.
 */
public class AddTodoCommand extends Command{
    private String taskDesc;

    /**
     * Constructs an `AddTodoCommand` object with the specified user input and due date string.
     *
     * @param taskDesc The task description.
     */
    public AddTodoCommand(String taskDesc) {
        this.taskDesc = taskDesc;
    }


    /**
     * Executes the command to add a ToDos task to the task list, update the storage, and notify the user interface.
     *
     * @param task The task list to which the ToDos task will be added.
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks.
     */
    @Override
    public void execute(TaskList task, Ui ui, FileHandler f) {
        ToDos newtodo = new ToDos(taskDesc);
        if (newtodo.isValid()) {
            task.add(newtodo);
            f.writeTasksToFile(task);
            ui.addedTodo(newtodo);
        }
    }

    /**
     * Check whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
