package TaskPackage;

import DukePackage.DukeException;
import ToolsPackage.Ui;

public class Task {

    private String task;
    private boolean done;

    public Task(String task, String done) throws DukeException {

        // Throws error if there is no task description.
        if (task.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Task description should not be empty.");
        }

        this.task = task;
        this.done = done.equals("1 ");
    }

    /**
     * Function to print task, together with whether it is marked
     * done or not done.
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
     * Function to toggle done or undone, depending on parameter.
     * @param keyword
     */
    public void toggleDone(String keyword, Ui ui) {
        this.done = keyword.equals("mark");
        ui.toggleDone(this, keyword);
    }

    public String addToStorage() {
        if (this.done) {
            return String.format("| 1 | %s", this.task);
        } else {
            return String.format("| 0 | %s", this.task);
        }
    }

    /**
     * Checks whether the given keyword is found in the task string.
     *
     * @param word Keyword to find in the task.
     * @return boolean Whether the word is inside.
     */
    public boolean matchKeyword(String word) {
        return this.task.contains(word);
    }
}
