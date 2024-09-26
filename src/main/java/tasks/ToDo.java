package tasks;

import exceptions.IncorrectToDoUpdateArgException;

/**
 * ToDos are tasks that have no date/time attached to them.
 *
 * @author Sebastian Tay
 */
public final class ToDo extends Task {
    static final String SYMBOL = "T";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return a new task with the updated description but same completion status.
     *
     * @param newDescription is the description that the task is to be updated with.
     * @throws exceptions.IncorrectInputException when the newDescription is empty.
     */
    public void edit(String newDescription) throws exceptions.IncorrectInputException {
        if (newDescription.trim().equals("")) {
            throw new IncorrectToDoUpdateArgException("");
        }

        super.editDescription(newDescription);
    }

    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "][" + getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String convertToStorageForm() {
        final String separator = "::";
        final String status = isDone() ? "1" : "0";

        return SYMBOL + separator + status + separator + getDescription();
    }
}
