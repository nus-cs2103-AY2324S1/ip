package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    /**
     * Constructs a task list with existing tasks.
     *
     * @param tasks The initial list of tasks to populate the task list.
     */
    public TaskList(List<Task> tasks) {
        this.taskList.addAll(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        this.taskList.remove(taskIndex);
    }

    /**
     * Retrieves a task from the TaskList based on the task index.
     *
     * @param taskIndex The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTaskAtIndex(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Retrieves all tasks from the task list.
     *
     * @return the list of tasks from the task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Filters the TaskList based on the provided condition.
     *
     * @param condition The condition to filter the TaskList with.
     * @return A new TaskList containing only tasks that satisfy the condition.
     */
    public TaskList filter(Predicate<Task> condition) {
        List<Task> filteredTasks = taskList.stream()
                .filter(condition)
                .collect(Collectors.toList());
        return new TaskList(filteredTasks);
    }


    /**
     * Returns the number of tasks in the TaskList.
     */
    public int getLength() {
        return this.taskList.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.getLength() == 0;
    }

}
