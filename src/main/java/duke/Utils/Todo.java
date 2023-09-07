package duke.Utils;

public class Todo extends Task {

    protected Todo(String title) {
        super(title, Task.Type.TODO);
    }

    protected Todo(String title, boolean marked) {
        this(title);
        if (marked) {
            this.mark();
        }
    }
    protected static Todo of(String[] args) throws InvalidFileDataException {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        return new Todo(title, marked);
    }

    @Override
    public String toCsv() {
        return FileIO.joinCsv(this.type(), this.marked(), this.name());
    }

    @Override
    public String toString() {
        return this.type() + super.toString();
    }
}
