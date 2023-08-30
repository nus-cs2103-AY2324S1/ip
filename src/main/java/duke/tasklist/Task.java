package duke.tasklist;

import java.time.LocalDate;

public abstract class Task {
    private final String name;
    private int state;

    public Task(String name) {
        this.name = name;
        this.state = 0;
    }

    public static Task of(String name) {
        return new Todo(name);
    }

    public static Task of(String name, LocalDate time) {
        return new Deadline(name, time);
    }

    public static Task of(String name, LocalDate startTime, LocalDate endTime) {
        return new Event(name, startTime, endTime);
    }
    public boolean mark() {
        if (state == 1) {
            return false;
        } else {
            this.state = 1;
            return true;
        }
    }

    public boolean unmark() {
        if (state == 0) {
            return false;
        } else {
            this.state = 0;
            return true;
        }
    }

    private String getState() {
        return state == 1 ? "[X]" : "[ ]";
    }


    public String getText() {
        return state + " | " + name;
    }
    @Override
    public String toString() {
        return getState() + " " + name;
    }
}
