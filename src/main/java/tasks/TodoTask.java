package tasks;

import enums.TaskType;

import java.util.Objects;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public String toString() {
        return TaskType.TODO.toSymbol() + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoTask)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}