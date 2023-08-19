public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
