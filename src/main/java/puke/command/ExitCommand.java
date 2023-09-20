package puke.command;

import puke.managers.PukeException;
import puke.managers.TaskList;

/**
 * A Command class that when executed, prints the exit message.
 */
public class ExitCommand extends Command {
    public static final String EXIT_MESSAGE = "It appears that the user has decided to close the program as indicated "
            + "by the command of which this is the function being issued and therefore, "
            + "I shall bid thee farewell and wish thee great fortune in your future endeavors.\n"
            + "Any input henceforth will close the certain and definite article of which has been known as PukeBot.";

    /**
     * Creates a new ExitCommand
     * @param rest the rest of the line
     * @throws PukeException if the format of the input is incorrect
     */
    public ExitCommand(String rest) throws PukeException {
        super(rest.isEmpty(), rest.isEmpty());
        if (!rest.isEmpty()) {
            throw new PukeException();
        }
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format.
     *
     * @param tl The task list.
     * @return the message String.
     */
    public String execute(TaskList tl) {
        if (!super.isValid) {
            return ERROR_MESSAGE;
        } else {
            return EXIT_MESSAGE;
        }
    }

    /**
     * Returns a boolean indicating if the other object is an instance of ExitCommand.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof ExitCommand);
    }
}
