// package main.java;

public class MarkedTask extends Task {
    MarkedTask(String content) {
        super(content);
    }

    Task unmark() {
        return new Task(this.content);
    }

    public String toString() {
        return String.format("[X] %s", content);
    }
}
