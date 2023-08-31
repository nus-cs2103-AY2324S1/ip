package duke.task;

import duke.Duke;

/**
 * A task that needs to be done.
 */
public final class TodoTask extends Task {
    /**
     * Creates a todo task.
     * @param task The input string.
     * @throws Duke.WrongFormatException If the input string is in the wrong format.
     */
    public TodoTask(String task) throws Duke.WrongFormatException {
        String description = getDescription(task);
        if (description == null) {
            throw new Duke.WrongFormatException("Whopsie daisies! I don't understand that format!");
        }
        this.description = description;
    }

    /**
     * Creates a todo task.
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     */
    public TodoTask(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    @Override
    protected String getTaskTypeString() {
        return squareBracketWrapper("T");
    }

    @Override
    protected String saveToFileString() {
        return "TODO | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    protected String getDescription(String input) {
        if (input.split(" ", 2).length == 1) {
            return null;
        }
        return input.split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description;
    }
}
