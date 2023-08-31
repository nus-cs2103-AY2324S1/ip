package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String data() {
        return "T " + super.data();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
