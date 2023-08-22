public class Task {
    Boolean done = false;
    String task;
    int taskId;

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

    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return taskId + "." + checkbox + task;
    }
    public String checkBox() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        return checkbox + task;
    }
}
