/**
 * Enum representing the types of tasks.
 */
enum TaskType {
    TODO, DEADLINE, EVENT
}
/**
 * Represents a task with a description and status.
 */
public class Task {
    private TaskType type;
    private String description;
    private boolean done;

    /**
     * Creates a new task with the specified type and description.
     *
     * @param type        The type of the task (TODO, DEADLINE, EVENT).
     * @param description The description of the task.
     */
    public Task(TaskType type, String description) {
        this.description = description;
        this.type = type;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string showing the task's type, status, and description.
     */
    @Override
    public String toString() {
        String printType;
        switch (type) {
            case TODO:
                printType = "T";
                break;
            case EVENT:
                printType = "E";
                break;
            case DEADLINE:
                printType = "D";
                break;
            default:
                printType = "";
                break;
        }
        String indicator = done ? "X" : " ";
        return "[" + printType + "][" + indicator + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    public String transformFormat() {
        String printType;
        switch (type) {
        case TODO:
            printType = "T";
            break;
        case EVENT:
            printType = "E";
            break;
        case DEADLINE:
            printType = "D";
            break;
        default:
            printType = "";
            break;
        }
        return printType + " | " + this.done + " | " + this.description;
    }

}
