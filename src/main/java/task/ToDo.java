package task;

import exceptions.DukeException;

/**
 * The ToDo class represents a simple task without a specific due date or time.
 * It provides methods to create a ToDo task and generate file and display strings.
 */
public class ToDo extends Task {
    private String title;

    /**
     * Constructs a ToDo object with the specified response string.
     *
     * @param response The user's input representing the task.
     */
    public ToDo(String response) {
        super(false);
        this.title = response;
    }

    public ToDo(String title, Boolean isDone) {
        super(isDone);
        this.title = title;
    }

    @Override
    public void editTitle(String newTitle) {
        this.title = newTitle;
    }

    @Override
    public void editDeadline(String newDeadline) throws DukeException {
        if (!false) {
            throw new DukeException("This task does not have a deadline!");
        }
    }

    @Override
    public void editStart(String newStart) throws DukeException {
        if (!false) {
            throw new DukeException("This task does not have a start end!");
        }
    }

    @Override
    public void editEnd(String newEnd) throws DukeException {
        if (!false) {
            throw new DukeException("This task does not have an end time!");
        }
    }

    /**
     * Generates a string representation of the ToDo task for storage in a file.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public Boolean compareTitle(String query) {
        return this.title.contains(query);
    }

    @Override
    public String toFileString() {
        if (this.done) {
            return "T | 1 | " + this.title;
        }
        return "T | 0 | " + this.title;
    }

    /**
     * Generates a string representation of the ToDo task for display purposes.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        if (this.done) {
            return "[T] " + "[X] " + this.title;
        }
        return "[T] " + "[ ] " + this.title;
    }
}
