package Task;

import Exception.DukeException;

/**
 * ToDo class is a subclass of Task class.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class.
     * @param description user provided description.
     * @throws DukeException organic exception by Duke.
     */
    public ToDo(String description) throws DukeException {
        super(description);
    }

    /**
     * storeFormat() method.
     * @return the specific format in which the task should be saved in the data file.
     */
    @Override
    public String storeFormat() {
        String taskType = "T";
        String isTaskDone;

        if (this.isDone) {
            isTaskDone = "1";
        } else {
            isTaskDone = "0";
        }

        return (taskType + " | " + isTaskDone + " | " + this.description.trim());
    }

    /**
     * toString() method.
     * @return string text of the task in which the task will be displayed in the terminal.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
