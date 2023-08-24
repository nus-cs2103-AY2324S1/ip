public class Todo extends Task {

    /**
     * Constructs a Todo with the specified name.
     *
     * @param name The name of the todo task to be done.
     */
    private Todo(String name) {
        super(name);
    }

    /**
     * Parses the command string to create a Todo instance.
     *
     * @param input The command string.
     * @return A new Todo instance.
     * @throws DukeException If the input format is invalid.
     */
    public static Todo createFromCommandString(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Description for todo cannot be empty.");
        }
        return new Todo(input);
    }

    public static Todo fromFileFormat(String[] parts) {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        Todo todo = new Todo(name);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toFileFormat() {
        return "T|" + (isDone ? "1" : "0") + "|" + this.name + "||";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}