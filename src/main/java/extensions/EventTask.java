package extensions;

public class EventTask extends Task {

    protected String start;
    protected String end;

    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
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
