package duke.tasks;

public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object.
     *
     * @param description Description of the Todo.
     * @param isDone Status of the Todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns text representation of todo for data file.
     *
     * @return Text representation of Todo.
     */
    public String getTextRepresentation() {
        return "T | " + super.getTextRepresentation();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Todo)) {
            return false;
        } else {
            Todo todo = (Todo) o;
            return todo.description.equals(this.description)
                    && todo.isDone == this.isDone;
        }
    }
}
