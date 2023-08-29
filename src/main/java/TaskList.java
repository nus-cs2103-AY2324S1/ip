import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks = new ArrayList<Task>();
    int numTotalTasks = 0;
    int numIncompleteTasks = 0;

    TaskList() {

    }

    void addTask(Task t) {
        this.tasks.add(t);
        numTotalTasks++;
        numIncompleteTasks++;
    }

    void addTask(Task t, boolean isDone) {
        this.tasks.add(t);
        numTotalTasks++;
    }

    Task removeTask(int i) {
        Task t = tasks.get(i);
        tasks.remove(i);
        if (t.getIsDone()) {
            numIncompleteTasks--;
        }
        numTotalTasks--;
        return t;
    }

    void markAsDone(Task t) {
        if (!t.getIsDone()) {
            t.markAsDone();
            numIncompleteTasks--;
        }
    }

    void markAsNotDone(Task t) {
        if (t.getIsDone()) {
            t.markAsNotDone();
            numIncompleteTasks++;
        }
    }

    ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    int getNumTotalTasks() {
        return this.numTotalTasks;
    }

    int getNumIncompleteTasks() {
        return this.numIncompleteTasks;
    }

    String formatAllTasksForSaving() {
        String returnString = "";
        for (Task t : getAllTasks()) {
            returnString += t.formatTaskForSaving();
            returnString += "\n";
        }
        return returnString;
    }
}
