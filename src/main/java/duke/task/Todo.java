package duke.task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toSave() {
        return (super.isComplete ? "1 " : "0 ")  + "todo " + super.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
