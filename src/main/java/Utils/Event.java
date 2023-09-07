package Utils;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    protected Event(String title, LocalDateTime start, LocalDateTime end) {
        super(title, Task.Type.EVENT);
        this.start = start;
        this.end = end;
    }

    protected Event(String title, boolean marked, LocalDateTime start, LocalDateTime end) {
        this(title, start, end);
        if (marked) {
            this.mark();
        }
    }

    protected static Event of(String[] args) {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        LocalDateTime start = FileIO.assertDateTime(args[3]);
        LocalDateTime end = FileIO.assertDateTime(args[4]);
        return new Event(title, marked, start, end);
    }

    @Override
    public String toCsv() {
        return FileIO.joinCsv(
            this.type(), 
            this.marked(), 
            this.name(), 
            Task.dateToString(this.start), 
            Task.dateToString(this.end)
          );
    }

    @Override
    public String toString() {
        return this.type() 
            + super.toString() 
            + " (from: " + Task.dateToString(this.start) 
            + " to: " + Task.dateToString(this.end) 
            + ")";
    }
}
