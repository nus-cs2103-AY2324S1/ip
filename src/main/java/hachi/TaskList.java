package hachi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Wrapper class that handles all interaction with the task list.
 * Internally stores the task list as an ArrayList of Tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks the specified task as completed.
     * @param i The task number.
     * @return The marked Task for chaining.
     */
    public Task mark(int i) {
        taskList.get(i).mark();
        return taskList.get(i);
    }

    /**
     * Marks the specified task as not completed.
     * @param i The task number.
     * @return The unmarked Task for chaining.
     */
    public Task unmark(int i) {
        taskList.get(i).unmark();
        return taskList.get(i);
    }

    /**
     * Removes the specified task.
     * @param i The task number.
     * @return The removed Task for chaining.
     */
    public Task remove(int i) {
        return taskList.remove(i);
    }

    /**
     * Adds the provided task to the task list.
     * @param t The given task.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    /**
     * Applies the consumer function to every task in the task list.
     * @param c The consumer function to be applied.
     */
    public void iter(Consumer<? super Task> c) {
        taskList.forEach(c);
    }

    /**
     * Filters the task list, returning a task list containing only the filtered elements which
     * pred returns true on.
     * @param pred The predicate that is being used to test the elements.
     * @return The filtered list.
     */
    public TaskList filter(Predicate<? super Task> pred) {
        ArrayList<Task> newTaskList = new ArrayList<>();
        taskList.forEach(x -> {
            if (pred.test(x)) {
                newTaskList.add(x);
            }
        });
        return new TaskList(newTaskList);
    }

    public void sort(Comparator<? super Task> c) {
        taskList.sort(c);
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            result += (num + ". " + taskList.get(i));
            if (i != taskList.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }

}
