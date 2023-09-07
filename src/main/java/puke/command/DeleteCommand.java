package puke.command;

import puke.managers.DataHandler;
import puke.managers.TaskList;
import puke.task.Task;


/**
 * A Command class that when executed, deletes a task in the task list
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand
     * @param rest the rest of the input line.
     */
    public DeleteCommand(String rest) {
        super(false, true);
        this.index = Integer.parseInt(rest);
    }

    /**
     * Executes the command by printing out the corresponding message.
     * If the command is invalid due to the index being out of bounds, prints an error message instead.
     *
     * @param tl The task list.
     * @return the message String.
     */
    public String execute(TaskList tl) {
        try {
            Task hold = tl.delete(this.index);
            DataHandler.writeToDatabase(tl);
            return generateMessage(hold, tl);
        } catch (Exception PukeException) {
            return ERROR_MESSAGE;
        }
    }

    private String generateMessage(Task task, TaskList tl) {
        return "I have acknowledged your request to have the task allocated to the specific index at which "
                + "you have mentioned removed from the collection of all\n"
                + "such tasks, colloquially known as your To Do list.\n"
                + "The task in question that has been deleted is: "
                + task
                + "\n"
                + "As of this current moment, there are a total of "
                + tl.size()
                + " occurrences of tasks in your list.";
    }

    /**
     * Returns a boolean indicating if the other object has the same toString() as this one.
     *
     * @param other Another object.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DeleteCommand && other.toString().equals(this.toString()));
    }

    /**
     * Returns a string representing this command.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "delete " + this.index;
    }
}
