package duke.task;

/**
 * Task needed to be done
 *
 * @author Lian Zhi Xuan
 */
public class ToDo extends Task {

    public ToDo(String task) {

        super(task);
    }

    @Override
    public String getStatus() {
        return isDone() ? "[T][X]" : "[T][ ]";
    }

    @Override
    public String getType() {
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
