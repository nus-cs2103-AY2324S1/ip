package task;

public class Todo extends Task {
    public Todo(String name) {
        super(name, TaskTypes.TODO);
    }

    public Todo(String name, boolean status) {
        super(name, TaskTypes.TODO, status);
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[T]%s %s", statusMark, name);
    }
}
