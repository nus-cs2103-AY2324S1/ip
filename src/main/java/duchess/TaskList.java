package duchess;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Represents a list of tasks. This is a wrapper around a ArrayList&lt;Task&gt;.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor. Initializes a Task List with an empty list of tasks.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor taking in a predefined ArrayList of tasks. Initializes a Task List with the same elements
     * as the provided ArrayList.
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
     * Copy constructor taking in a TaskList. Initializes a new Task List with the same elements as the provided
     * TaskList. Tasks are not deepcopied, so both TaskLists will now reference the same Tasks.
     *
     * @param tasksList - a TaskList.
     */
    TaskList(TaskList tasksList) {
        this.tasks = new ArrayList<>();

        for (Task t : tasksList.tasks) {
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

    /**
     * Filters this TaskList, returning a new TaskList where elements that do not satisfy the predicate are removed.
     *
     * @param predicate - the predicate to test for.
     * @return            the TaskList with the filter applied.
     */
    public TaskList filter(Predicate<? super Task> predicate) {
        TaskList newTaskList = new TaskList(this);

        // Iterate backwards because we are deleting elements from the array while accessing
        for (int i = newTaskList.size() - 1; i >= 0; i--) {
            Task t = this.getTask(i);

            // Fails the predicate, should be removed.
            if (!predicate.test(t)) {
                newTaskList.removeTask(i);
            }
        }

        return newTaskList;
    }

    /**
     * Filters this TaskList, returning a new TaskList where elements that do not satisfy the predicate
     * are replaced with a null value instead.
     *
     * @param predicate - the predicate to test for.
     * @return            the TaskList with the filter applied.
     */
    public TaskList filterReplaceNull(Predicate<? super Task> predicate) {
        TaskList newTaskList = new TaskList(this);

        // Iterate backwards because we are deleting elements from the array while accessing
        for (int i = newTaskList.size() - 1; i >= 0; i--) {
            Task t = this.getTask(i);

            // Fails the predicate, should be removed.
            if (!predicate.test(t)) {
                newTaskList.tasks.set(i, null);
            }
        }

        return newTaskList;
    }

    /**
     * Executes a given function for each element in this array.
     *
     * @param consumer - the Consumer to be executed.
     */
    public void forEach(Consumer<? super Task> consumer) {
        this.tasks.forEach(consumer);
    }

    /**
     * Executes a given function for each (element, index) pair in this array.
     *
     * @param consumer - the BiConsumer to be executed.
     */
    public void forEach(BiConsumer<? super Task, ? super Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            Task t = this.getTask(i);

            consumer.accept(t, i);
        }
    }
}
