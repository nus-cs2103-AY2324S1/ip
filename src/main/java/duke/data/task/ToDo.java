package duke.data.task;
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public  String saveString() {
        return "T" + super.saveString();
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}