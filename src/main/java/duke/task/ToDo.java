package duke.task;

public class ToDo extends Task {
    public ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T]" + "[X] " + this.taskName;
        } else {
            return "[T]" + "[ ] " + this.taskName;
        }
    }

    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "T/@/1/@/" + this.taskName;
        } else {
            return  "T/@/0/@/" + this.taskName;
        }
    }

    @Override
    public String toUpdateString(int i) {
        return "T/@/" + i + "/@/" + this.taskName;
    }
}
