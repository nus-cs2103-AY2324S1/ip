package alcazar.tasks;

/**
 * Encapsulates a Task
 */
public class Task {
    /** Boolean showing whether this task has been marked */
    private boolean isMarked;
    /** The input prompt describing this task */
    private String taskPrompt;

    /**
     * Constructs a new Task
     * @param taskPrompt Input description of this task
     */
    Task(String taskPrompt) {
        this.taskPrompt = taskPrompt;
        this.isMarked = false;
    }

    /**
     * Marks this task done.
     */
    public void markTask() {
        this.isMarked = true;
    }

    /**
     * Marks this task undone.
     */
    public void unmarkTask() {
        this.isMarked = false;
    }
    /**
     * Evaluates String form of a Task
     * @return String form of a Task
     */
    public String toString() {
        String marked;
        if (this.isMarked) {
            marked = "X";
        } else {
            marked = " ";
        }
        return "[" + marked + "] " + this.taskPrompt;
    }
}
