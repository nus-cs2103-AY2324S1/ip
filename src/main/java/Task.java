// package main.java;

public class Task {
    String content;
    boolean marked;

    Task(String content) {
        this.content = content;
        this.marked = false;
    }

    Task(String content, boolean status) {
        this.content = content;
        this.marked = status;
    }

    Task mark() {
        return new Task(content, true);
    }

    Task unmark() {
        return new Task(content);
    }

    String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "added: " + this.content + "\n" +
                "____________________________________________________________";
    }

    public String toString() {
        if (!this.marked) {
            return String.format("[ ] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }
}
