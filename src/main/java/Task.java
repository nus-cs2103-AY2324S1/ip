public class Task {
    String taskName;
    boolean status;

    public Task(Boolean status, String taskName) {
        this.taskName = taskName;
        this.status = status;
    }

    public String getName() {
        return this.taskName;
    }

    public void mark() {
        this.status = true;
    }

    public void unMark() {
        this.status = false;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public String toStoreString() {
        if (this.status) {
            return "1/@/" + this.taskName;
        } else {
            return  "0/@/" + this.taskName;
        }
    }

    public String toUpdateString(int i) {
        return i + "/@/" + this.taskName;
    }
}
