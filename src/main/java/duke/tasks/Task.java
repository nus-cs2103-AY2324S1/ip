package duke.tasks;

abstract public class Task {
    private int status;
    private String task;

    public Task(int status, String task) {
        this.status = status;
        this.task = task;
    }

    abstract public String convertTask();

    public boolean canMark() {
        if (this.status == 0) {
            this.status = 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean canUnMark() {
        if (this.status != 0) {
            this.status = 0;
            return true;
        } else {
            return false;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        if (status == 0) {
            return "[ ] " + task;
        } else {
            return "[X] " + task;
        }
    }
}