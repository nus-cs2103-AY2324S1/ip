package duke;

import java.util.ArrayList;
import java.util.function.Predicate;
import task.Task;
public class TaskList {
    private ArrayList<Task> arrList;

    public TaskList() {
        this.arrList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrList) {
        this.arrList = arrList;
    }

    public ArrayList<Task> getArrList() {
        return this.arrList;
    }

    public int size() {
        return this.arrList.size();
    }
    public void mark(int choice) {
        this.arrList.get(choice - 1).markDone();
    }

    public void unmark(int choice) {
        this.arrList.get(choice - 1).markUndone();
    }

    public void add(Task task) {
        this.arrList.add(task);
    }
    public Task delete(int choice) {
        return this.arrList.remove(choice - 1);
    }

    /**
     * Returns a list of tasks that contain the word as the name.
     *
     * @param word String input by user representing the task name to correspond to
     * @return ArrayList of tasks that corresponds to the word input
     */
    public ArrayList<Task> find(String word) {
        Predicate<Task> findName = task -> task.containName(word);
        ArrayList<Task> tempList = new ArrayList<>();
        for (Task task : this.arrList) {
            if (findName.test(task)) {
                tempList.add(task);
            }
        }
        return tempList;
    }

    public String taskToString(int choice) {
        return this.arrList.get(choice - 1).toString();
    }
}
