package duke;

import java.time.LocalDate;

public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ Command.TODO.getCommand(), this.getDone() ? "1" : "0", this.getTaskName() };
        return String.join(" / ", state);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }
}
