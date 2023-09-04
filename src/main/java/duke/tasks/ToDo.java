package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Encapsulates a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo object with the given description.
     *
     * @param description The description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo object with a given description as found in the command.
     *
     * @param command The command to create the todo.
     * @return The todo object constructed from the given command.
     */
    public static ToDo createToDoFromCommand(String command) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDo(command.substring(5));
    }

    /**
     * Constructs a ToDo object with the given description and done status.
     *
     * @param storageString The string from duke.txt representing a row of data for a todo.
     * @return The todo object constructed from the given storage string.
     */
    public static ToDo createToDoFromStorage(String storageString) {
        String[] split = storageString.split(" \\| ");
        String isDone = split[1];
        String taskDescription = split[2];
        ToDo todo = new ToDo(taskDescription);
        if (isDone.equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo for storage in duke.txt.
     *
     * @return The string representation of the todo for storage in duke.txt.
     */
    public String toStorageString() {
        return "T" + super.toStorageString();
    }
}
