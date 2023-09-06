package bot.task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private final ArrayList<Task> lst;

    /**
     * Creates an instance of an empty TaskList object
     */
    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    /**
     * Creates an instance of a TaskList object containing a list of Tasks.
     *
     * @param lst List of Tasks
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Adds a new Task object into the current TaskList.
     *
     * @param task
     */
    public void add(Task task) {
        this.lst.add(task);
    }

    /**
     * Returns a Task object based on the index. idx argument is the index of the desired task.
     *
     * @param idx
     * @return
     */
    public Task get(int idx) {
        return this.lst.get(idx);
    }

    /**
     * Returns an integer based on the size of the ArrayList object.
     *
     * @return number of tasks stored within the TaskList object.
     */
    public int length() {
        return this.lst.size();
    }

    /**
     * Removes a specific task at a particular index of the ArrayList.
     *
     * @param idx index of task to be deleted.
     */
    public void delete(int idx) {
        this.lst.remove(idx);
    }

    /**
     * Returns an Iterator object of Task type for iteration.
     *
     * @return Iterator of tasks
     */
    public Iterator<Task> iterator() {
        return this.lst.iterator();
    }

    /**
     * Returns a String of all the tasks stored within the TaskList object.
     *
     * @return a string of all the tasks to be outputted to the user.
     */
    public String list() {
        String str = "";
        Iterator<Task> iterator = this.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            str += Integer.toString(i) + ". " + iterator.next() + "\n";
            i++;
        }
        return str;
    }
}
