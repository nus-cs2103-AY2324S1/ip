package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.gui.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

/**
 * Represents a command to mark a task as uncompleted in the Jarvis app.
 */
public class UnmarkCommand implements Command {
    private String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the unmark command by marking the specified task as uncompleted.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     * @throws InvalidIndexException If the task index provided in the user input is
     *                               invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        String[] splitUserInput = userInput.split(" ");
        int index = Integer.parseInt(splitUserInput[1]);

        if (userInput.length() <= 4) {
            ui.printResponse("Master, please indicate which task you wish to mark DONE?\n" +
                    "from 1 to " + taskList.getTaskCount());
        } else if (index >= 1 && index <= taskList.getTaskCount()) {
            Task task = taskList.getTask(index - 1);
            task.unmarkCompleted();
            storage.saveTasks(taskList.getTaskList());
            ui.printTaskStatus(task);
        } else {
            throw new InvalidIndexException(null);
        }
    }
}
