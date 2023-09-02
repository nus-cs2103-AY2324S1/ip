import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + statusIcon + "] " + this.name;
    }

    public void markAsDone() {
        if (this.isDone) {
            System.out.println("You have already finished this task.");
        } else {
            this.isDone = true;
            String statusIcon = getStatusIcon();
            System.out.println("Nice! I've marked this task as done:\n" + "  " + this);
        }
    }

    public void markAsNotDone() {
        if (!this.isDone) {
            System.out.println("You have not marked this task as done.");
        } else {
            this.isDone = false;
            String statusIcon = getStatusIcon();
            System.out.println("OK, I've marked this task as not done yet:\n" + "  " + this);
        }
    }
}
