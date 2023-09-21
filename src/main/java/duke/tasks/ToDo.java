package duke.tasks;

import duke.exceptions.InsufficientArgumentsException;

/**
 * Represents a task with a description.
 */
public class ToDo extends Task {
    public static final String TASK_TYPE = "todo";
    public static final String TASK_CODE = "T";

    /**
     * Creates a new {@code ToDo} instance.
     *
     * @param description The description of the to-do.
     * @param isDone      The indication of the event being to-do.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description) {
        this(description, false);
    }


    /**
     * Parses storage input to create a {@code ToDo} instance.
     *
     * @param input The storage input string.
     * @return The created ToDo.
     * @throws InsufficientArgumentsException If the string input has insufficient arguments to
     *                                        create an {@code ToDo}.
     */
    public static ToDo parseStorageInput(String input) {
        boolean isDone = input.charAt(0) == '1';
        String description = input.substring(4);
        return new ToDo(description, isDone);
    }

    /**
     * Parses user input to create a {@code ToDo} instance.
     *
     * @param input The user input string.
     * @return The created ToDo.
     */
    public static ToDo parseUserInput(String input) {
        return new ToDo(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("%s | %d | %s", ToDo.TASK_CODE, this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s]", TASK_CODE) + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ToDo) {
            ToDo otherTodo = (ToDo) obj;
            return this.description.equals(otherTodo.description) && this.isDone == otherTodo.isDone;
        }
        return false;
    }
}
