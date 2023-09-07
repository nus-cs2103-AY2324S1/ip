package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    private boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() throws AlreadyMarkedException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new AlreadyMarkedException();
        }
    }

    public void markUndone() throws AlreadyUnmarkedException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new AlreadyUnmarkedException();
        }
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + name : "[ ] " + name;
    }

    public abstract String getAddMessage();

    /**
     * Searches the description of the Task to find the given search term.
     * @param searchTerm The term to search for.
     * @return True if the search term is found, false otherwise.
     */
    public abstract boolean contains(String searchTerm);

}
