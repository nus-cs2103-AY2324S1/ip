package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the exit command, indicating that the chatbot should end.
     * Also saves tasks to storage and displays the outro message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for data persistence.
     * @return A farewell message to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.showOutro();
        return "See you around!";
    }
}
