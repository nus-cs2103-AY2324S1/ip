package duke.task;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Initialises a task list with the given list of tasks.
     * 
     * @param list The list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.list.remove(index);
    }

    /**
     * Returns the task at the given index.
     * 
     * @param index The index of the task to be returned.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     * 
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    public TaskList findTasks(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        matchingTasks = list.stream()
                .filter(task -> task.description.contains(searchTerm))
                .collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(matchingTasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList taskList = (TaskList) obj;
            return taskList.list.equals(this.list);
        }
        return false;
    }

    /**
     * Returns a stream of the tasks in the task list.
     * 
     * @return A stream of the tasks in the task list.
     */
    public Stream<Task> stream() {
        return this.list.stream();
    }
}
