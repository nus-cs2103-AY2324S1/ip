package alcazar;
import java.util.ArrayList;

/**
 * Class for the functionality related to storing the list of tasks
 */
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

    /**
     * Prints the tasks containg String search
     * @param search Phrase to equate the tasks against
     * @return The tasks containing search
     */
    public String printEquals(String search) {
        String listedTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(search)) {
                listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        if (listedTasks.equals("")) {
            return "Sorry no matching tasks.";
        } else {
            return listedTasks;
        }

    }
    /**
     * The method evaluates the list of the passed tasks.
     * @return String of all the passed tasks
     */
    public String getTasks() {
        String listedTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return listedTasks;
    }



}
