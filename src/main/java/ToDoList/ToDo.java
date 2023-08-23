package ToDoList;

import java.util.ArrayList;

public class ToDo {
    private final String name;
    private boolean isDone;

    public ToDo(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void mark() {
        this.setIsDone(true);
    }

    public void unmark() {
        this.setIsDone(false);
    }

    public String isDone() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.isDone() + "] " + this.name;
    }
}
