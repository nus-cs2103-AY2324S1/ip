package alcazar;
public class Task {
    private boolean isMarked;
    private String taskPrompt;

    Task(String taskPrompt) {
        this.taskPrompt = taskPrompt;
        this.isMarked = false;
    }

    /**
     * Used to mark this task done.
     */
    public void markTask() {
        this.isMarked = true;
    }

    /**
     * Used to mark this task undone.
     */
    public void unmarkTask() {
        this.isMarked = false;
    }
    /**
     * Used to evaluate String form of a Task
     * @return String form of a Task
     */
    public String toString() {
        String marked;
        if(this.isMarked) {
            marked = "X";
        } else {
            marked = " ";
        }
        return "[" + marked + "] " + this.taskPrompt;
    }
}
