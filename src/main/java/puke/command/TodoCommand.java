package puke.command;

import puke.managers.DataHandler;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.task.ToDo;

/**
 * A Command class that when executed, creates a new Todo task
 */
public class TodoCommand extends Command {
    private final String desc;

    /**
     * Creates a new ToDoCommand
     * @param rest the rest of the input line.
     */
    public TodoCommand(String rest) {
        super(false, !rest.isEmpty());
        this.desc = rest;
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
            tl.add(new ToDo(this.desc));
            DataHandler.writeToDatabase(tl);
            return generateMessage(tl);
        } catch (Exception PukeException) {
            return ERROR_MESSAGE;
        }
    }
    private String generateMessage(TaskList tl) throws PukeException {
        return "Understood. I have hereby created a task known to require doing at a future time "
                + "but with no such time being specified and inserted it into "
                + "the overall collection of said tasks that require action. "
                + "Here is a display of the added deadline task: \n"
                + tl.get(tl.size() - 1)
                + "\n"
                + "You now, in total, have "
                + tl.size()
                + " of these tasks recorded within said collection.";
    }

    /**
     * Returns a boolean indicating if the other object has the same toString as this command and is an instance of
     * TodoCommand.
     *
     * @param other Another object.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof TodoCommand && other.toString().equals(this.toString()));
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
            return new ToDo(this.desc).toString();
        } catch (PukeException e) {
            throw new RuntimeException(e);
        }
    }
}
