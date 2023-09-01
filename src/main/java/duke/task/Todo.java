package duke.task;

public class Todo extends Task {
    public Todo(String name) {
        this.name = name;
        this.status = false;
        this.type = TaskTypes.TODO;
    }

    public Todo(String name, boolean status) {
        this.name = name;
        this.status = status;
        this.type = TaskTypes.TODO;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[T]%s %s", statusMark, name);
    }

    @Override
    public String toSave() {
        return String.format("T%s%s%s%d", DISCRIMINATOR, name, DISCRIMINATOR, Boolean.compare(this.status, false));
    }
}
