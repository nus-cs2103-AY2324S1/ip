package benben;


import java.util.Objects;

public class Todo extends Task {
    public Todo(String description) {

        super(description);
    }

    @Override
    public String toString() {

        return "[T] " + super.toString();
    }

    @Override
    public String getLog() {
        return "T | " + (isDone? "1" : "0")
                + " | " + this.description + System.lineSeparator();
    }

    @Override
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        }

        if (!(task instanceof Todo)) {
            return false;
        }

        Todo t = (Todo) task;

        return Objects.equals(t.getLog(), this.getLog());
    }
}