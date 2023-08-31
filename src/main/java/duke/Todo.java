package duke;

public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";
        return "T|" + completedString + description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
