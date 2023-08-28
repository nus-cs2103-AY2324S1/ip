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

    @Override
    public String toSave() {
        return String.format("T%s%s%s%d", DISCRIMINATOR, name, DISCRIMINATOR, Boolean.compare(status, false));
    }
}
