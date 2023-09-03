package alcazar;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public int size() {
        return this.tasks.size();
    }

    public void markElement(int index) {
        this.tasks.get(index).markTask();
    }

    public void unmarkElement(int index) {
        this.tasks.get(index).unmarkTask();
    }

    public Task elementAt(int index) {
        return this.tasks.get(index);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }
    public void printEquals(String search) {
        String listedTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(search)) {
                listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        if (listedTasks.equals("")) {
            System.out.println("Sorry no matching tasks.");
        } else {
            System.out.println(listedTasks);
        }

    }
    /**
     * The method evaluates the list of the passed tasks.
     * @return String of all the passed tasks
     */
    public String getTasks() {
        String listedTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return listedTasks;
    }



}
