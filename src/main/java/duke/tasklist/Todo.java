package duke.tasklist;

class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
