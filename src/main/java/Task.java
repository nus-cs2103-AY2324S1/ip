import java.sql.Array;
import java.util.ArrayList;

public abstract class Task {
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

//    static int getNumIncompleteTasks() {
//        int totalCount = 0;
//        for (Task t : allTasks) {
//            if (!t.getDone()) {
//                totalCount++;
//            }
//        }
//        return totalCount;
//    }

    abstract String getTaskType();
}
