package duck.task;

import duck.DuckException;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void mark() throws DuckException {
        if (this.isDone == true) {
            throw new DuckException("Error - Task is already marked.");
        } else {
            this.isDone = true;
        }
    }

    public void unmark() throws DuckException {
        if (this.isDone == false) {
            throw new DuckException("Error - Task is already unmarked.");
        } else {
            this.isDone = false;
        }
    }

    public boolean containsKeyword(String keyword) {
        return this.name.contains(keyword);
    }

    public String stringify() {
        String done = String.valueOf(this.isDone ? '1' : '0');
        String nameLength = String.valueOf(this.name.length()) + "/";
        return done + nameLength + this.name;
    }

    @Override
    public String toString() {
        char done = this.isDone ? 'X' : ' ';
        String str = "[" + done + "] " + name;
        return str;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task otherTask = (Task) other;
            return this.name.equals(otherTask.name) && this.isDone == otherTask.isDone;
        }
        return false;
    }
}