package duke.task;

import duke.InvalidTaskCreationException;

import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo ToDoCon(String description) throws InvalidTaskCreationException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    public LocalDateTime getUrgencyDate() {
        return LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
