package duke;

/** Abstraction of a task that has no deadline */
public class ToDo extends Task {

    /**
     * Creates a task with no deadline
     *
     * @param description What the task is about.
     * @param isDone whether the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "T " + isCompleted + " " + this.description;
    }

    @Override
    public String update(String specifications) {
        String[] splits = specifications.split(" ", 2);
        String updateType = splits[0];
        String description = splits.length > 1 ? splits[1] : "";
        if (description.isEmpty() || !updateType.equals("/description")) {
            throw new IllegalArgumentException("OOPS!!! "
                    + "Update of todo task description must in format "
                    + "/description <description>");
        }
        this.description = description;
        return "Ok, I've updated the todo task to the following:\n" + this;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
