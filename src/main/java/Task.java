public class Task {
    private String action;
    private boolean completed;

    public Task(String action) {
        this.action = action;
        this.completed = false;
    }

    public void completeTask() {
        this.completed = true;
    }

    public void displayTask(int index) {
        if (this.completed) {
            System.out.println(String.format("\t \t \t \t %d) ✅ ".concat(this.action), index));
        } else {
            System.out.println(String.format("\t \t \t \t %d) ⬜ ".concat(this.action), index));
        }
    }

    public void revertTask() {
        this.completed = false;
    }
}
