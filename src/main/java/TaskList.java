import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor. Initializes a Task List with an empty list of tasks.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor taking in a predefined ArrayList of tasks. Initializes a Task List with the same elements as the provided ArrayList.
     *
     * @param tasksArrayList - an ArrayList of Tasks.
     */
    TaskList(ArrayList<Task> tasksArrayList) {
        this.tasks = new ArrayList<>();

        for (Task t : tasksArrayList) {
            this.addTask(t);
        }
    }

    /**
     * Returns the size of this TaskList.
     *
     * @return the size of this TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the index of a particular Task in this array.
     *
     * @param t - the Task to search for in this array.
     * @return    the index of that Task, or -1 if that Task is not present in the list.
     */
    public int indexOf(Task t) {
        return this.tasks.indexOf(t);
    }

    /**
     * Appends a specified Task to the end of this TaskList.
     *
     * @param t - the Task to be appended.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Retrieves a Task at a specific index.
     *
     * @param index - the index of the Task to be retrieved.
     * @return        the Task that is retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a task from this task list at the specified index.
     *
     * @param index - the index of the Task to be removed.
     * @return        the Task that was removed.
     */
    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }
}
