package Duke.Tasks;

public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + "| " + super.toString();
    }
}
