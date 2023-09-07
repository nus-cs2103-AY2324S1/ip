package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.task.Deadline;

/**
 * A Command class that when executed, creates a Deadline Task
 */
public class DeadlineCommand extends Command {
    private final String[] rest;

    /**
     * Creates a new DeadlineCommand
     * @param rest the rest of the input line.
     */
    public DeadlineCommand(String rest) {
        super(false, !rest.isEmpty());
        this.rest = rest.split(" /");
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is in the wrong format, prints an error message instead.
     *
     * @param tl The task list.
     * @return the message String.
     */
    public String execute(TaskList tl) {
        try {
            tl.add(new Deadline(this.rest));
            DataHandler.writeToDatabase(tl);
            return generateMessage(tl);
        } catch (Exception PukeException) {
            return ERROR_MESSAGE;
        }
    }

    private String generateMessage(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require doing at a future time "
                + "alongside the stipulated time that you have indicated and inserted it into "
                + "the overall collection of these tasks that require action.\n"
                + "Here is a display of the added deadline task: "
                + tl.get(tl.size() - 1)
                + "\n"
                + "You now, in total, have "
                + tl.size()
                + " of these tasks recorded within said collection.";
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of
     * DeadlineCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DeadlineCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a String representation of this command.
     *
     * @return a String.
     * @throws RuntimeException If an incorrect format is used
     */
    @Override
    public String toString() {
        try {
            return new Deadline(this.rest).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}
