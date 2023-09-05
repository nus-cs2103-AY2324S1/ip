package duke;

/**
 * Class to handle the task
 */
public class Task {
    private String list;
    private boolean isCompleted;
    private TaskType type;

    /**
     * Constructor
     * @param list user list
     * @param type user input type
     */
    public Task(String list, TaskType type) {
        this.list = list;
        this.type = type;
    }

    /**
     * Marks the task as isCompleted and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful marking of the task.
     * @throws DukeException If the task has already been marked as done.
     */
    public String setMarked() throws DukeException {
        if (this.isCompleted) {
            throw new DukeException("This task has already been marked as done!\n");
        }
        this.isCompleted = true;
        return "";
    }

    /**
     * Marks the task as not isCompleted and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful unmarking of the task.
     * @throws DukeException If the task has already been marked as not done.
     */
    public String setUnmarked() throws DukeException {
        if (!this.isCompleted) {
            throw new DukeException("This task has already been marked as not done!\n");
        }
        this.isCompleted = false;
        return "";
    }

    @Override
    public String toString() {
        String typeSymbol = "";
        switch (type) {
        case TODO:
            typeSymbol = "[T]";
            break;
        case EVENT:
            typeSymbol = "[E]";
            break;
        case DEADLINE:
            typeSymbol = "[D]";
            break;
        default:
            break;
        }
        return typeSymbol + (this.isCompleted ? "[X] " : "[ ] ") + this.list;
    }

}
