package taskpackage;

import dukepackage.DukeException;

import toolpackage.Ui;

public class Task {

    private String task;
    private boolean isDone;

    public Task(String task, String isDone) throws DukeException {
        // Throws error if there is no task description.
        if (task.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Task description should not be empty.");
        }

        this.task = task;
        this.isDone = isDone.equals("1 ");
    }

    /**
     * Function to print task, together with whether it is marked
     * done or not done.
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
     * Function to toggle done or undone, depending on parameter.
     * @param keyword
     */
    public void toggleDone(String keyword, Ui ui) {
        this.isDone = keyword.equals("mark");
        ui.toggleDone(this, keyword);
    }

    public String addToStorage() {
        if (this.isDone) {
            return String.format("| 1 | %s", this.task);
        } else {
            return String.format("| 0 | %s", this.task);
        }
    }
}
