package duke;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String fileFormat() {
        return String.format("T%s%s", Storage.SEPARATOR, super.fileFormat());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return super.equals(o);
        }
        return false;
    }
}
