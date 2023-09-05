package command;

import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command that specifically exits the ChatBot.
 */
public class ExitCommand extends Command {
    /** Command the user starts with to activate the ExitCommand. */
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructs an ExitCommand with an Index.
     * @param index
     */
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
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String message = "Bye. Hope to see you again soon lol!";
        //        ui.print(message);
        return message;
    }
}
