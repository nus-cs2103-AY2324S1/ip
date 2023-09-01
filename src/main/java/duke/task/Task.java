package duke.task;

public class Task {
    String taskName;
    boolean isDone;

    public Task(Boolean status, String taskName) {
        this.taskName = taskName;
        this.isDone = status;
    }

    public String getName() {
        return this.taskName;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public String toStoreString() {
        if (this.isDone) {
            return "1/@/" + this.taskName;
        } else {
            return  "0/@/" + this.taskName;
        }
    }

    public String toUpdateString(int i) {
        return i + "/@/" + this.taskName;
    }
}
