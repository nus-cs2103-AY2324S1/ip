package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `ListCommand` class represents a command to list all tasks.
 * It is responsible for retrieving the list of tasks from the task list,
 * formatting them, and providing a response message.
 */
public class ListCommand extends Command {
    /**
     * Executes the "List" command. It retrieves the list of tasks from the task list,
     * formats them into a response message, and returns the message to be displayed to the user.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be displayed.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message containing the list of tasks.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.printListReply(taskList);
    }
}
