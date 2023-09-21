package duke.utilities;

import java.io.Serializable;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * A list of tasks the user inputs.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> taskList;

    /**
     * TaskList class constructor.
     * Instantiates an empty arraylist as the taskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * TaskList class constructor.
     * Instantiates taskList as the given arrayList.
     *
     * @param taskList Instantiate taskList with given array:List.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the task at the specific index.
     *
     * @param index The index of task.
     * @return task at the index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Gets the size of taskList.
     *
     * @return size of taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds task to the back of the taskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task at the specific index.
     *
     * @param index The index of task to be deleted.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException(String.format("I do not understand. You do not have a task %d", index));
        }
        this.taskList.remove(index);
    }

    /**
     * A getter function that returns the taskList itself.
     * @return The taskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Marks task as done.
     *
     * @param index The index of task to be marked.
     */
    public void markAsDone(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException(String.format("I do not understand. You do not have a task %d", index));
        }
        this.taskList.get(index).markDone();
    }

    /**
     * Marks task as not done.
     *
     * @param index The index of task to be marked.
     */
    public void markAsNotDone(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException(String.format("I do not understand. You do not have a task %d", index));
        }
        this.taskList.get(index).markNotDone();
    }

    /**
     * Filters list according to keyword.
     *
     * @param keyword The keyword used to filter the list.
     * @return TaskList The new list with the words required added.
     */
    public TaskList filter(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.toString().contains(keyword)) {
                filteredList.add(task);
            }
        }
        return new TaskList(filteredList);
    }

    /**
     * Checks if the task is present in the list.
     *
     * @param task The task to be checked.
     * @return true if the task is in the list and false otherwise.
     */
    public boolean contains(Task task) {
        return taskList.contains(task);
    }

}
