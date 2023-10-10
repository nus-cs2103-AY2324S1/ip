package jarvis.commands;

import jarvis.exceptions.InvalidIndexException;
import jarvis.ui.Ui;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

/**
 * Represents a command to mark a task as completed in the Jarvis app.
 */
public class MarkCommand implements Command {

    private String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the mark command by marking the specified task as completed.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     * @throws InvalidIndexException If the task index provided in the user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        if (userInput.length() <= 4) {
            return ui.printResponse("Master, please indicate which task you wish to mark DONE?\n"
                    + "from 1 to " + taskList.getTaskCount());
        }
        assert taskList != null && ui != null && storage != null;

        String[] splitUserInput = userInput.split(" ");
        int index = Integer.parseInt(splitUserInput[1]);
        if (index < 1 && index > taskList.getTaskCount()) {
            throw new InvalidIndexException(null);
        }

        return markTask(taskList, ui, storage, index);
    }

    private static String markTask(TaskList taskList, Ui ui, Storage storage, int index) {
        Task task = taskList.getTask(index - 1);
        task.markCompleted();
        storage.saveTasks(taskList.getTaskList());
        return ui.printTaskStatus(task);
    }
}
