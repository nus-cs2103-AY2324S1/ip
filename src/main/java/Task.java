public class Task {
    private String name;
    private boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }
    public void setUndone() {
        this.isDone = false;
    }

    public String showTask() {
        return this.name;
    }

    private String statusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String showTaskinList() {
        return this.statusIcon() + " " + this.showTask();
    }


}
