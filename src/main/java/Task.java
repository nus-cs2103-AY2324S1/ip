// package main.java;

public class Task {
    String content;

    Task(String content) {
        this.content = content;
    }

    MarkedTask mark() {
        return new MarkedTask(content);
    }

    Task unmark() {
        throw new IllegalArgumentException("attemp to unmark unmarked task");
    }

    public String toString() {
        return String.format("[ ] %s", content);
    }
}
