package duke.tasks;

import duke.exceptions.DukeException;

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

    public static ToDo createToDoFromStorage(String storageString) {
        String[] split = storageString.split(" \\| ");
        String isDone = split[1];
        String taskDescription = split[2];
        ToDo todo = new ToDo (taskDescription);
        if (isDone.equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStorageString() {
        return "T" + super.toStorageString();
    }
}
