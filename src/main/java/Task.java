import java.util.ArrayList;

public abstract class Task {
    static ArrayList<Task> allTasks = new ArrayList<Task>();
    static int numIncompleteTasks = 0;

    String name;
    boolean isDone;

    Task(String name) {
        allTasks.add(this);
        this.name = name;
        this.isDone = false;
        Task.numIncompleteTasks++;
    }

    static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    static Task removeTask(int index) {
        // Takes in an index (starting from 0 to n-1) and removes that task from Task.allTasks
        Task removedTask = allTasks.get(index);
        allTasks.remove(index);
        return removedTask;
    }

    boolean markAsDone() {
        this.isDone = true;
        Task.numIncompleteTasks--;
        return isDone();
    }

    boolean markAsNotDone() {
        this.isDone = false;
        Task.numIncompleteTasks++;
        return isDone();
    }

    boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }

    static int getNumIncompleteTasks() {
        return Task.numIncompleteTasks;
    }

    abstract String getTaskType();

    static String formatAllTasksForSaving() {
        String returnString = "";
        for (Task t : getAllTasks()) {
            returnString += t.toString();
            returnString += "\n";
        }
        return returnString;
    }



}
