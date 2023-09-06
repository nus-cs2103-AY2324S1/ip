package monke.commands;

import monke.Storage;
import monke.TaskList;
import monke.Ui;

/**
 * The ExitCommand class represents a command to exit the Monke application.
 * It extends the Command class.
 */
public class ExitCommand extends Command {
    /** The command word for parser to recognize this command. */
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the ExitCommand to exit the application.
     *
     * @param ui      The user interface.
     * @param storage The storage to read and write data.
     * @param tasks   The list of tasks.
     * @return response given by Monke.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return "Bye. Hope to see you again soon! OOGA BOOGA!\n";
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}