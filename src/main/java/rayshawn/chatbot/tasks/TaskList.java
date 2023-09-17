package rayshawn.chatbot.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire task list. Contains the data of the tasklist.
 */
public class TaskList {

    private final List<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param taskList existing list
     */
    public TaskList(List<Task> taskList) {
        assert taskList != null : "taskList should not be null";
        this.list = new ArrayList<>(taskList);
    }

    /**
     * Adds task to the list.
     *
     * @param task to be added
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        this.list.add(task);
    }

    public Task getTask(int index) {
        assert index >= 0 && index < list.size() : "Index out of bounds";
        return list.get(index);
    }

    /**
     * Remove a pre-existing task.
     *
     * @param index index of task to be removed
     */
    public void removeTask(int index) {
        assert index >= 0 && index < list.size() : "Index out of bounds";
        list.remove(index);
    }

    public int count() {
        return list.size();
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(list);
    }

}
