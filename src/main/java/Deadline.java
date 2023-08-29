public class Deadline extends Task {
    private String deadline;
    public Deadline(boolean isDone, String taskName, String deadline) {
        super(isDone, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D]" + "[X] " + this.taskName + "(by:" + this.deadline + ")";
        } else {
            return "[D]" + "[ ] " + this.taskName + "(by:" + this.deadline + ")";
        }
    }

    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "D/@/1/@/" + this.taskName + "/@/" + this.deadline;
        } else {
            return  "D/@/0/@/" + this.taskName + "/@/" + this.deadline;
        }
    }

    @Override
    public String toUpdateString(int i) {
        return "D/@/" + i + "/@/" + this.taskName + "/@/" + this.deadline;
    }
}
