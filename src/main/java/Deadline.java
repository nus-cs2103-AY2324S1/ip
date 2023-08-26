public class Deadline extends Task {
    private String deadline;
    public Deadline(boolean status, String taskName, String deadline) {
        super(status, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[D]" + "[X] " + this.taskName + "(by:" + this.deadline + ")";
        } else {
            return "[D]" + "[ ] " + this.taskName + "(by:" + this.deadline + ")";
        }
    }

    @Override
    public String toStoreString() {
        if (this.status) {
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
