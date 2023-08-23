package ipduke;

public class Todo extends Task{
    Todo(String taskDetails) {
        super(taskDetails);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
