package jarvis.commands;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

/**
 * Represents a command to delete a task in the Jarvis app.
 */
public class DeleteCommand implements Command {

    private String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the delete command by removing the specified task from the task
     * list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     * @throws InvalidIndexException If an invalid index is provided.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        String[] userInputSpilt = userInput.split(" ");
        int index = Integer.parseInt(userInputSpilt[1]);

        if (index >= 1 && index <= taskList.getTaskCount()) {
            Task removedTask = taskList.deleteTask(index - 1);
            storage.saveTasks(taskList.getTaskList());
            ui.printResponse("Noted Master! I've removed this task:\n" + "\t" + removedTask.toString() + "\n" +
                    "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
        } else {
            throw new InvalidIndexException("    Please indicate which task you wish to DELETE?\n" +
                    "    From 1 to " + taskList.getTaskCount() + "\n" +
                    "    Current Index: " + index);
        }
    }
}
