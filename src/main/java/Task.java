import java.sql.Array;
import java.util.ArrayList;

public class Task {
    static ArrayList<Task> allTasks = new ArrayList<Task>();
    String name;
    boolean isDone;

    Task(String name) {
        allTasks.add(this);
        this.name = name;
        this.isDone = false;
    }

    static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    boolean markAsDone() {
        this.isDone = true;
        return getDone();
    }

    boolean markAsNotDone() {
        this.isDone = false;
        return getDone();
    }

    boolean getDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
