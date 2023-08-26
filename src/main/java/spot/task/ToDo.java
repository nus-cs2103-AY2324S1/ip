package spot.task;

import java.time.LocalDate;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toLine() {
        return " T | " + super.toLine();
    }

    @Override
    public boolean fallsOn(LocalDate date) {
        return false;
    }
}
