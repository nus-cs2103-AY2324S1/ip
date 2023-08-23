import java.sql.Array;
import java.util.ArrayList;

public class Task {
    static ArrayList<Task> allTasks = new ArrayList<Task>();
    String name;

    Task(String name) {
        allTasks.add(this);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    static ArrayList<Task> getAllTasks() {
        return allTasks;
    }
}
