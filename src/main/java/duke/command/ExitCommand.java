package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to quit the chatbot program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the given ExitCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to run the command on (if needed).
     * @param ui The UI to print any output onto.
     * @param storage The storage to save and update tasks (if needed).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Bye. Hope to see you again soon!");
    }

    /**
     * Returns whether the ExitCommand should exit the program when run, which is true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Gets the command type for the ExitCommand.
     *
     * @return Exit.
     */
    @Override
    public String getCommandType() {
        return "Exit";
    }
}
