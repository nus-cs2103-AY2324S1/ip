package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class that contains the list of Tasks
 */
public class TaskList {
    private List<Task> lst;

    /**
     * Constructor for TaskList object
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Overloaded constructor for TaskList Object
     * @param listOfTasks the List we want to import from initially
     */
    public TaskList(List<Task> listOfTasks) {
        this.lst = listOfTasks;
    }

    public Task get(int i) {
        return lst.get(i);
    }
    public void remove(int i) {
        lst.remove(i);
    }

    public int size() {
        return this.lst.size();
    }

    public void add(Task t) {
        this.lst.add(t);
    }

}
