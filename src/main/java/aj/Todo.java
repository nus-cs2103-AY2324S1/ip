package aj;

class Todo extends Task {
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    Todo(String taskName, boolean isMarked) {
        super(taskName, isMarked);
    }
}
