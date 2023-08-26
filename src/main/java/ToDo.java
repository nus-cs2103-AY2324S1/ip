public class ToDo extends Task {
    public ToDo(boolean status, String taskName) {
        super(status, taskName);
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[T]" + "[X] " + this.taskName;
        } else {
            return "[T]" + "[ ] " + this.taskName;
        }
    }

    @Override
    public String toStoreString() {
        if (this.status) {
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
