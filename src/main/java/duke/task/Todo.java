package duke.task;

import java.time.LocalDate;

/**
 * Represents a Todo Task.
 * @author Toh Li Yuan (A0255811H)
 */
public class Todo extends Task {
    /**
     * Creates a new Todo Task.
     *
     * @param name the name of the Todo.
     */
    public Todo(String name) {
        this.name = name;
        this.isDone = false;
        this.type = TaskTypes.TODO;
    }

    /**
     * Creates a new Todo Task.
     *
     * @param name the name of the Todo.
     * @param status the completion status of the Todo.
     */
    public Todo(String name, boolean status) {
        this.name = name;
        this.isDone = status;
        this.type = TaskTypes.TODO;
    }

    @Override
    public String toString() {
        String statusMark = this.isDone ? "[✓]" : "[✕]";
        return String.format("[T]%s %s", statusMark, name);
    }

    @Override
    public String toSave() {
        return String.format("T%s%s%s%d", DISCRIMINATOR, name, DISCRIMINATOR, Boolean.compare(this.isDone, false));
    }

    @Override
    public String getReminder(LocalDate currDate, int days) {
        return null;
    }
}
