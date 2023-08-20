public class ToDo extends Task {
    
    public ToDo (String description) {
        super(description);
    }

    public static ToDo createToDoFromCommand(String command) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDo (command.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
