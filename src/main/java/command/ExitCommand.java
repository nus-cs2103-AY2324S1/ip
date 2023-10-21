package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        super(true);
    }


    /**
     * Executes the exit command, displaying a farewell message.
     *
     * @param taskList The task list (not used in this command).
     * @param storage  The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Bye! Have a nice day!";
    }
}
