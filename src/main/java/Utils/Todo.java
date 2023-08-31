package Utils;

public class Todo extends Task {

    protected Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
