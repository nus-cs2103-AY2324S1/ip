public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toSavedString() {
        return String.format("[T] %s//", super.toSavedString());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
