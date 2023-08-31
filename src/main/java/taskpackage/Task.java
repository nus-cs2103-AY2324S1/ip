package taskpackage;

import dukepackage.DukeException;

import toolpackage.Ui;

public class Task {

    private String task;
    private boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param task Task to complete.
     * @param isDone Indicator of whether task has been completed.
     * @throws DukeException if the task is missing.
     */
    public Task(String task, String isDone) throws DukeException {

        // Throws error if there is no task description.
        if (task.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Task description should not be empty.");
        }

        this.task = task;
        this.isDone = isDone.equals("1 ");
    }

    /**
     * Prints tasks for user to see.
     *
     * @return String
     */
    public String printTask() {
        if (this.isDone) {
            return String.format("[X] %s", this.task);
        } else {
            return String.format("[] %s", this.task);
        }
    }

    /**
     * Toggles the completed status of the task.
     *
     * @param keyword Word to indicate whether to mark the task as complete or incomplete.
     * @parma ui UI of the bot.
     */
    public void toggleDone(String keyword, Ui ui) {
        this.isDone = keyword.equals("mark");
        ui.toggleDone(this, keyword);
    }

    /**
     * Formats and returns task for storage.
     *
     * @return String
     */
    public String addToStorage() {
        if (this.isDone) {
            return String.format("| 1 | %s", this.task);
        } else {
            return String.format("| 0 | %s", this.task);
        }
    }
}
