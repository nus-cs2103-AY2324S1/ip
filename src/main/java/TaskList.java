import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }
    public TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    public Task getTask(int index) {
       return this.tasklist.get(index);
    }

    public void addTask (Task newtask) {
        this.tasklist.add(newtask);
    }

    public void deleteTask(int index) {
        this.tasklist.remove(index);
    }

    public void markTask(int index) {
        this.tasklist.get(index).markAsDone();
    }
    public void unmarkTask(int index) {
        this.tasklist.get(index).markAsNotDone();
    }

    public int size() {
        return this.tasklist.size();
    }
}
