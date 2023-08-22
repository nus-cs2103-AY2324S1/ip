public class Todo extends Task {

    private Todo(String name) {
        super(name);
    }

    public static Todo createFromCommandString(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Description for todo cannot be empty.");
        }
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}