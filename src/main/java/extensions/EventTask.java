package extensions;

public class EventTask extends Task {

    protected String start;
    protected String end;

    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public EventTask(String desc, String start, String end, int isMarked) {
        super(desc);
        this.start = start;
        this.end = end;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("E|%d|%s|%s|%s", this.isDone ? 1 : 0, this.desc, this.end, this.start);
    }

    @Override
    public String toString() {
        String output = String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.start,
                this.end);
        return output;
    }

}
