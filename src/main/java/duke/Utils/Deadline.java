package duke.Utils;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime start;

    protected Deadline(String title, LocalDateTime start) {
        super(title, Task.Type.DEADLINE);
        this.start = start;
    }

    protected Deadline(String title, boolean marked, LocalDateTime start) {
        this(title, start);
        if (marked) {
            this.mark();
        }
    }

    protected static Deadline of(String[] args) {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        LocalDateTime start = FileIO.assertDateTime(args[3]);
        return new Deadline(title, marked, start);
    }

    @Override
    public String toCsv() {
        return FileIO.joinCsv(
            this.type(), 
            this.marked(), 
            this.name(), 
            Task.dateToString(start)
          );
    }
    
    @Override
    public String toString() {
        return this.type() + super.toString() + " (by: " + Task.dateToString(start) + ")";
    }
}
