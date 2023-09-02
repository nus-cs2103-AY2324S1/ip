package duke.task;

public class ToDo extends Task {

    public ToDo(String task) {

        super(task);
    }

    @Override
    public String status() {
        return isDone() ? "[T][X]" : "[T][ ]";
    }

    @Override
    public String type() {
        return "Task.ToDo";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDo) {
            ToDo temp = (ToDo) o;
            return super.equals(temp);
        }
        return false;
    }
}
