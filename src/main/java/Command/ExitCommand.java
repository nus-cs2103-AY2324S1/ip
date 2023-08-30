package Command;

import Exception.*;
import Helper.*;
import Task.*;

/**
 * Represents a Command that specifically exits the ChatBot.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand(int index) {
        super(index);
    }

    /**
     * Returns if the Command is exiting the ChatBot
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits the ChatBot.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.print("Bye. Hope to see you again soon lol!");
    }
}
