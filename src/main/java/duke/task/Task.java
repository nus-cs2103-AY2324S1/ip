package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundException;
import duke.exception.DukeNoTimeException;
import duke.exception.DukeNotTaskException;

import java.time.format.DateTimeParseException;

/**
 * A Class that handle task
 *
 * @author marioalvaro
 */
public class Task {
    /**
     * the task description
     */
    protected String description;
    /**
     * variable to indicate if it is marked or not
     */
    protected boolean isDone;

    /**
     * Constructor for duke.task.Task class
     * @param description the string of description that would like to be stored
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * factory method to create duke.task based on the type
     * @param splitTask String array containing the type and description
     * @return new duke.task.Task object based on the types
     * @throws DukeException throws duke.exception.DukeException
     */
    public static Task createTaskType(String[] splitTask) throws DukeException {
        String type = splitTask[0];

        String description = "";

        if (!(type.equals("deadline") || type.equals("todo") || type.equals("event"))) {
            throw new DukeNotTaskException(type);
        }

        try {
            description = splitTask[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundException(splitTask[0]);
        }

        try {
            if (type.equals("deadline")) {
                String[] splitDesc = description.split(" /by ", 2);
                return new Deadline(splitDesc[0], splitDesc[1]);
            } else if (type.equals("todo")) {
                return new Todo(description);
            } else {
                assert type.equals("event") : "Type should be Event";

                String[] splitDesc = description.split(" /from ", 2);
                String[] splitDesc2 = splitDesc[1].split(" /to ", 2);
                return new Event(splitDesc[0], splitDesc2[0], splitDesc2[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeNoTimeException(type);
        } catch (DateTimeParseException e) {
            throw new DukeNoTimeException(type);
        }
    }

    /**
     * return the status icon
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * mark the duke.task done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * mark the duke.task not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * return true if the description contains the input string.
     * @param str input string
     * @return true if the description contains the input string
     */
    public boolean contains(String str) {
        return this.description.contains(str);
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Convert task to string with storing format.
     *
     * @return String with data format
     */
    public String toDataString() {
        if (this.isDone) {
            return "1 / " + this.description;
        } else {
            return "0 / " + this.description;
        }
    }

    /**
     * Equals method to help to check duplicates
     * @param obj obj compared with
     * @return if its is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Task) {
            Task task = (Task) obj;

            if (this.description == task.description) {
                return true;
            }

            if (this.description == null || task.description == null) {
                return false;
            }

            return description.equals(task.description);
        }

        return false;
    }

    /**
     * Check if the text is equal.
     *
     * @param obj Object to compare with.
     * @return if the text is equal or not.
     */
    public boolean equalsText(Object obj) {
        return this.equals(obj);
    }
}

