package TaskPackage;

import DukePackage.DukeException;

public class ToDos extends Task {

    /**
     * Constructs a new Task.
     *
     * @param task Task to complete.
     * @param done Indicator of whether task has been completed.
     * @throws DukeException if the task is missing.
     */
    public ToDos(String task, String done) throws DukeException {
        super(task, done);
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
