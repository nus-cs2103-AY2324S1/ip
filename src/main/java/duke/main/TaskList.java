package duke.main;

import java.util.ArrayList;

import duke.exception.DukeDuplicatedTaskException;
import duke.task.Task;

/**
 * A class for storing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task into TaskList.
     * @param task
     */
    public void add(Task task) throws DukeDuplicatedTaskException {
        if (duplicateExist(task)) {
            throw new DukeDuplicatedTaskException();
        }
        tasks.add(task);
    }

    /**
     * Checks if the task entered is duplicated.
     * @param task
     * @return
     */
    public boolean duplicateExist(Task task) {
        long duplicate = tasks.stream()
                .filter(taskInList -> taskInList.toString().equals(task.toString()))
                .count();
        if (duplicate > 0) {
            return true;
        }
        return false;
    }

    /**
     * Removes and returns the index-th task from TaskList.
     * @param index
     * @return
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns size of TaskList.
     * @return
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the index-th task from TaskList.
     * @param index
     * @return
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Finds all tasks that contains keywords in their descriptions.
     * @param keyword
     * @return
     */
    public String find(String keyword) {
        String matchingTasks = "";
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.showContent().contains(keyword)) {
                matchingTasks += String.format("  %d. %s\n", index, currentTask.toString().trim());
                index += 1;
            }
        }
        return matchingTasks;
    }
}
