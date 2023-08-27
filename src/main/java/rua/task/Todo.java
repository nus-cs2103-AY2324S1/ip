package rua.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean marked) {
        super(description, marked);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public Todo mark() {
        return new Todo(this.description, true);
    }

    @Override
    public Todo unmark() {
        return new Todo(this.description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
