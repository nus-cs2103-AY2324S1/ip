package sana;
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * returns string representation of todo task.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String formatTask() {
        return "T" + super.formatTask();
    }

}
