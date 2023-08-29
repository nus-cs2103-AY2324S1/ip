package duke;

public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    @Override
    public String saveString() {
        String completedString = completed ? "X|" : " |";
        return "T|" + completedString + task;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
