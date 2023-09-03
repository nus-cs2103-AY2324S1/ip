package Task;

import Exception.DukeException;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
    }
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
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
