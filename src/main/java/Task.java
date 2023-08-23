public class Task {
    Boolean done = false;
    String task;
    int taskId;
    static String line = "______________________________________________________________________________________\n";

    public Task(String task, int taskId) {
        this.task = task;
        this.taskId = taskId;
    }

    public void mark() {
        this.done = true;
    }
    public void unMark() {
        this.done = false;
    }

    public String line() {
        return line;
    }

    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return taskId + "." + checkbox + task;
    }
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + task;
    }
}
