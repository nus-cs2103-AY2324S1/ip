package rua.task;

import rua.command.ClearCommand;

public class Todo extends Task {
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Todo)) {
            return false;
        }
        Todo c = (Todo) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description) &&
                c.marked.equals(this.marked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
