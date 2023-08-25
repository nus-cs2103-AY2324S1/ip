public class Task {
    private String taskName;
    private boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    public String getName() {
        return this.taskName;
    }

    public String getStatus() {
        return this.status ? "[X]" : "[ ]";
    }

    public void changeStatus() {
        this.status = !this.status;
    }

}
