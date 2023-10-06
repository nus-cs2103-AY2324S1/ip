package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a todo task in Duke.
 */
public class Todo extends Task {
    private static final Pattern PATTERN_COMMAND_CREATE_TODO = Pattern.compile("^(todo|t)( (?<taskName>.*))?");

    /**
     * Constructs a Todo with the specified name.
     *
     * @param name The name of the todo task
     */
    Todo(String name) {
        super(name);
    }

    /**
     * Constructs a Todo with the specified name and done status.
     *
     * @param isDone Boolean indicating if the todo task is done
     * @param name   The name of the todo task
     */
    Todo(boolean isDone, String name) {
        super(name, isDone);
    }

    /**
     * Creates and Returns a Todo
     *
     * @param command String command that specifies the values the created Todo object should have
     * @return Todo created
     * @throws LukeException If the command is of an invalid format
     */
    public static Todo createTodo(String command) throws LukeException {
        Matcher matcher = PATTERN_COMMAND_CREATE_TODO.matcher(command);
        boolean found = matcher.find();
        assert(found);

        String taskName = matcher.group("taskName");
        if (taskName == null || taskName.isBlank()) {
            throw new LukeException("The description of a todo cannot be empty.");
        }

        return new Todo(taskName);
    }

    /**
     * Creates and Returns a Todo
     * This method assumes correct ordering of args provided
     *
     * @param args Arguments used to create the Todo from its constructor
     * @param isDone Boolean indicating if the Todo is done
     * @return Todo created
     * @throws LukeException If there is insufficient/ excessive number of arguments in args
     */
    public static Todo createTodo(String[] args, boolean isDone) throws LukeException {
        if (args.length != 1) {
            throw new LukeException("Error creating Todo: Incorrect number of arguments");
        }

        return new Todo(isDone, args[0]);
    }

    /**
     * Converts the Todo object to a string for saving to a file.
     *
     * @return String representation of the Todo for saving
     */
    @Override
    public String toSaveStr() {
        return "T" + " | " + super.toSaveStr();
    }

    /**
     * Determines if this Event is equal to another object
     *
     * @param o Other object to be compared with
     * @return true if o is an Todo and satisfies the equals condition of its superclass
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return super.equals(o);
        }

        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
