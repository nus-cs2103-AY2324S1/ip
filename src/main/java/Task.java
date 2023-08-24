public class Task {
    private boolean isMarked;
    private String taskPrompt;

    Task(String taskPrompt) {
        this.taskPrompt = taskPrompt;
        this.isMarked = false;
    }
    public void markTask() {
        this.isMarked = true;
    }

    public void unmarkTask() {
        this.isMarked = false;
    }
    public String toString() {
        String marked = "";
        if(this.isMarked) {
            marked = "X";
        } else {
            marked = " ";
        }
        return "[" + marked + "] " + this.taskPrompt;
    }
}
