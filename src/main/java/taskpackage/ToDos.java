package taskpackage;

import dukepackage.DukeException;

public class ToDos extends Task {

    /**
     * Constructs a new Task.
     *
     * @param task Task to complete.
     * @param isDone Indicator of whether task has been completed.
     * @throws DukeException if the task is missing.
     */
    public ToDos(String task, String isDone) throws DukeException {
        super(task, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToStorage() {
        return String.format("T %s%n", super.addToStorage());
    }
}
