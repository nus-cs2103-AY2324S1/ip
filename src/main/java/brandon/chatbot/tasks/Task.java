package brandon.chatbot.tasks;

import brandon.chatbot.common.DukeException;

/**
 * Represents a Task to be done by the user.
 */
public class Task {
    private String title;
    private boolean isDone;

    /**
     * Validates the title and creates a Task object.
     *
     * @param title of the task.
     * @throws DukeException if the title is blank.
     */
    public Task(String title) throws DukeException {
        if (title.isBlank()) {
            throw new DukeException("    Title of a " + this + " cannot be blank...\n--------------------------------");
        }
        this.title = title;
        this.isDone = false;
    }

    /**
     * Sets the done value of the Task, which indicates if the task is done.
     *
     * @param isDone indicates whether the task is done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the String value of the status of the task, which includes the done state and the title.
     *
     * @return status of the task.
     */
    public String getStatus() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.title;
    }

    /**
     * Returns the title of the task in String.
     *
     * @return title of the task.
     */
    public String toString() {
        return title;
    }
}
