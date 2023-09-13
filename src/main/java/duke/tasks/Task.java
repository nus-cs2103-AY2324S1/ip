package duke.tasks;

import duke.exceptions.DukeException;

public abstract class Task {
    private final String descr;
    private boolean isDone;

    public Task(String descr) {
        this.descr = descr;
        this.isDone = false;
    }


    public void markDone() throws DukeException {
        assert !isDone : "Can't do a 'done' task!";
        if (isDone) {
            throw new DukeException("This task is already marked done!");
        } else {
            isDone = true;
        }
    }

    public void markUndone() throws DukeException {
        assert isDone : "Can't mark an undone task as 'done'";
        if (!isDone) {
            throw new DukeException("This task is already marked undone!");
        } else {
            isDone = false;
        }
    }
    public String status() {
        return (isDone ? "1" : "0");
    }

    public String marking() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method that converts task to string.
     *
     * @return formatted String version of the task
     */
    public String toString() {
        assert this.descr.split(" ").length > 1 : "Invalid task description";
        String keyword = this.descr.split(" ")[0];
        String taskDescription = this.descr.substring(keyword.length()).trim();
        return "[" + marking() + "] " + taskDescription;
    }

}
