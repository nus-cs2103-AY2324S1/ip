package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an Exit command to be executed.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the list of commands to exit the chatbot.
     *
     * @param list The given TaskList associated with the chatbot.
     * @param ui The given Ui to show the status of the exit command.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList);
        ui.printExitMessage();
    }
}
