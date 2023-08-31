package TaskPackage;

import DukePackage.DukeException;
import ToolsPackage.Ui;

public class Task {

    private String task;
    private boolean done;

    /**
     * Constructs a new Task.
     *
     * @param task Task to complete.
     * @param done Indicator of whether task has been completed.
     * @throws DukeException if the task is missing.
     */
    public Task(String task, String done) throws DukeException {

        // Throws error if there is no task description.
        if (task.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Task description should not be empty.");
        }

        this.task = task;
        this.done = done.equals("1 ");
    }

    /**
     * Prints tasks for user to see.
     *
     * @return String
     */
    public String printTask() {
        if (this.done) {
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
        this.done = keyword.equals("mark");
        ui.toggleDone(this, keyword);
    }

    /**
     * Formats and returns task for storage.
     *
     * @return String
     */
    public String addToStorage() {
        if (this.done) {
            return String.format("| 1 | %s", this.task);
        } else {
            return String.format("| 0 | %s", this.task);
        }
    }
}
