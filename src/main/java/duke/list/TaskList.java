package duke.list;

import java.util.ArrayList;
import java.util.List;

import duke.exception.KoraException;
import duke.task.Task;

/**
 * Task list class for storing the current tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor of TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns task at specified index.
     * @param taskIndex Task index of task.
     * @return Task.
     * @throws KoraException When task index is more than the number of task in task list.
     */
    public Task getTask(int taskIndex) throws KoraException {
        try {
            return tasks.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new KoraException("AiGu! There are only " + tasks.size() + " tasks!");
        }
    }

    /**
     * Removes task at specified index.
     * @param taskIndex Task index of task to be removed.
     */
    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    /**
     * Returns the length of task list.
     * @return Integer number of tasks in the task list.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns formatted String of task list to be saved in Storage.
     * @return String formatted.
     */
    public String saveFormat() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + tasks.get(i).saveFormat();
        }
        return output;
    }

    /**
     * Removes all tasks in task list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Adds all tasks in the list to current task list.
     * @param list List of tasks to be added.
     */
    public void addTaskList(List<Task> list) {
        tasks.addAll(list);
    }

    /**
     * Displays all tasks in the task list.
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + String.format("%d", i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
