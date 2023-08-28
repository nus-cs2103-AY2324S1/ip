package duke.data.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String getStorageString() {
        return "T | " + super.getStorageString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return this.description.equals(todo.description);
        }

        return false;
    }
}
