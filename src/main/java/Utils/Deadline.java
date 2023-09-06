package Utils;

public class Deadline extends Task {

    private String start;

    protected Deadline(String title, String start) {
        super(title, Task.Type.DEADLINE);
        this.start = start;
    }

    protected Deadline(String title, boolean marked, String start) {
        this(title, start);
        if (marked) {
            this.mark();
        }
    }

    protected static Deadline of(String[] args) {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        String start = FileIO.assertString(args[3]);
        return new Deadline(title, marked, start);
    }

    @Override
    public String toCsv() {
        return FileIO.joinCsv(this.type(), this.marked(), this.name(), this.start);
    }
    
    @Override
    public String toString() {
        return this.type() + super.toString() + " (by: " + this.start + ")";
    }
}
