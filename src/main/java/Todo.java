public class Todo extends Task{
    Todo(String description) {
        super(description);
    }

    Todo(String description, Boolean marked) {
        super(description, marked);
    }

    @Override
    String getType() {
        return "T";
    }

    @Override
    Todo mark() {
        return new Todo(this.description, true);
    }

    @Override
    Todo unmark() {
        return new Todo(this.description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
