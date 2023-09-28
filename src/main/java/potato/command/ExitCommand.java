package potato.command;

import java.io.IOException;

import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The ExitCommand class represents a command for exiting the programme.
 * It extends the Command class and specifies the behavior of executing an "exit" command.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Executes the exit command, which exits the programme.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the exit of the programme.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}
