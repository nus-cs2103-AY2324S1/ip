// package main.java;

public class ToDo extends Task {
    ToDo(String content) {
        super(content);
    }

    ToDo(String content, boolean status) {
        super(content, status);
    }

    ToDo mark() {
        return new ToDo(content, true);
    }

    ToDo unmark() {
        return new ToDo(content);
    }

    String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                toString() + "\n" +
                String.format("Now you have %d tasks in the list,\n", listSize) +
                "____________________________________________________________";
    }

    public String toString() {
        if (!this.marked) {
            return String.format("[T][ ] %s", content);
        } else {
            return String.format("[T][X] %s", content);
        }
    }
}
