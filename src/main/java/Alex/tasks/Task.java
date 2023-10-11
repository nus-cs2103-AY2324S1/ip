package Alex.tasks;

import Alex.AlexException;

/**
 * A class that can be instantiated to represent a general task. Currently it has three subclasses which are
 * Task, Deadline and Event classes.
 */
public class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * The constructor of Task class when the task description string is passed in.
     *
     * @param description task description in string.
     * @throws AlexException if an error occurs when the task description is invalid or does not make sense.
     */
    public Task(String description) throws AlexException {
        description = description.stripTrailing();
        if (description.equals("")) {
            String message = "OOPS!!! The description of a task cannot be empty";
            throw new AlexException(message);
        }
        this.description = description;
    }

    public String getStatusSymbol() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * A method that is used to know whether this task is done.
     * @return A boolean value to indicate if the task is done, returns true if this task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * A method that is used to get the task description of this instance of task.
     *
     * @return the task description in string of this task instance.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A method that is used to mark this task to be done.
     */
    public String mark() {
        this.isDone = true;
        String tobePrinted = "Nice! I've marked this task as done:\n"
                    + "   "
                    + this;

        return tobePrinted;
    }

    /**
     * A method that is used to mark this task to be done with additional parameter to specify
     * whether or not to print the message to the user after marking.
     *
     * @param printToUser A boolean parameter to indicate whether or not to print message after marking.
     */
    public String mark(boolean printToUser) {
        String output = null;
        if (printToUser) {
            output = this.mark();
        } else {
            this.isDone = true;
        }
        return output;
    }

    /**
     * A method that is used to ummark this task to be undone.
     */
    public String unmark() {
        this.isDone = false;
        String tobePrinted = "OK, I've marked this task as not done yet:\n"
                    + "   "
                    + this;

        return tobePrinted;
    }

    @Override
    public String toString() {
        String str = "[" + this.getStatusSymbol() + "] " + this.description;
        return str;
    }
}
