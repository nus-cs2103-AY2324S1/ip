public class Event extends Task {
    private String start;
    private String end;
    public Event(boolean isDone, String taskName, String start, String end) {
        super(isDone, taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E]" + "[X] " + this.taskName + "(from:" + this.start + "to:" + this.end + ")";
        } else {
            return "[E]" + "[ ] " + this.taskName + "(from:" + this.start + "to:" + this.end + ")";
        }
    }

    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "E/@/1/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
        } else {
            return  "E/@/0/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
        }
    }

    @Override
    public String toUpdateString(int i) {
        return "E/@/" + i + "/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
    }
}

