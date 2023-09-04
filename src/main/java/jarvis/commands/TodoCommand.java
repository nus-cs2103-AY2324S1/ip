package jarvis.commands;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.tasks.TaskList;
import jarvis.tasks.Todo;

/**
 * Represents a command to add a "Todo" task in the Jarvis app.
 */
public class TodoCommand implements Command {

    private String userInput;

    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the todo command by adding a new "Todo" task to the task list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     * @throws InvalidIndexException      If an invalid index is provided.
     * @throws InvalidTaskFormatException If the task format is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidIndexException, InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("todo")) {
            throw new InvalidTaskFormatException(null);
        }
        String taskTitle = userInput.substring(5).trim();
        Todo todo = new Todo(taskTitle, false);
        taskList.addTask(todo);
        storage.saveTasks(taskList.getTaskList());
        ui.printResponse("Yes Master! I've added this task: \n" + "\t" + todo.toString() + "\n" +
                "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
    }
}
