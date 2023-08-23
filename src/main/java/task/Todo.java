package task;

public class Todo extends Task {
    public Todo(String name) {
        super(name, TaskTypes.TODO);
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[T]%s %s", statusMark, name);
    }
}
