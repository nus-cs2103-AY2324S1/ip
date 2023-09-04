package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand extends Command {

    /**
     * Message card for the Duke application.
     *
     * @param message The message to be wrapped in the messageCard.
     */
    private String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    /**
     * Executes the exit task command, exiting the Duke application.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(messageCard("Bye. Hope to see you again soon!"));
    }

    /**
     * Indicates whether this command is an exit command.
     * ExitCommand is an exit command, so this method returns true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
