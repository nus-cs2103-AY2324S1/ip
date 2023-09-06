package Utils;

public class Event extends Task {

    private String start;
    private String end;

    protected Event(String title, String start, String end) {
        super(title, Task.Type.EVENT);
        this.start = start;
        this.end = end;
    }

    protected Event(String title, boolean marked, String start, String end) {
        this(title, start, end);
        if (marked) {
            this.mark();
        }
    }

    protected static Event of(String[] args) {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        String start = FileIO.assertString(args[3]);
        String end = FileIO.assertString(args[4]);
        return new Event(title, marked, start, end);
    }

    @Override
    public String toCsv() {
        return FileIO.joinCsv(this.type(), this.marked(), this.name(), this.start, this.end);
    }

    @Override
    public String toString() {
        return this.type() + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
