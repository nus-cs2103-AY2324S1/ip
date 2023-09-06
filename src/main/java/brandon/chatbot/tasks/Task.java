package brandon.chatbot.tasks;

import brandon.chatbot.common.DukeException;

public class Task {
    private String title;
    private boolean done;

    public Task(String title) throws DukeException {
        if (title.isBlank()) {
            throw new DukeException("    Title of a " + this + " cannot be blank...\n--------------------------------");
        }
        this.title = title;
        this.done = false;
    }

    public boolean getDone() {
        return this.done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatus() {
        return "[" + (this.done ? "X" : " ") + "] " + this.title;
    }

    public String toString() {
        return title;
    }
}
