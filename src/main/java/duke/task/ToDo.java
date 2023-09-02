package duke.task;

public class ToDo extends Task {

    public ToDo(String name, String isDone) {
        super(name, isDone);
    }

    @Override
    public String toDataString() {
        return super.toDataString();
    }

    @Override
    public String toString() {
        String str = "[T] " + super.getStatus() + " " + super.name;
        return str;
    }



}
