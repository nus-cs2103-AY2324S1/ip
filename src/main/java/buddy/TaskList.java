package buddy;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TaskList class contains the task list and its related operations.
 * @author Lim Jin Yin
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final String filePath = "./data/tasks.txt";

    /**
     * The constructor for an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * The constructor for a (non-empty) TaskList.
     *
     * @param tasks The arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the specific task in this TaskList.
     *
     * @param taskIndex The zero-based index of the task.
     * @return Returns the task with the specified index.
     */
    public Task getTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "taskIndex should be between 0 and (size of task list - 1)";
        return tasks.get(taskIndex);
    }

    /**
     * Returns the list of tasks in this TaskList.
     *
     * @return Returns an arraylist of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in this TaskList.
     * @return Returns the size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task of type Task to this TaskList.
     * @param task The task that is added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the specified task from the task list.
     * @param taskIndex The zero-based index of the task.
     */
    public void deleteTask(int taskIndex) {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list to delete.");
        }
        tasks.remove(taskIndex);
    }

    /**
     * Marks the specified task in the task list as done.
     * @param taskIndex The zero-based index of the task.
     * @throws AssertionError If taskIndex is invalid.
     */
    public void markAsDone(int taskIndex) throws AssertionError {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "index should be between 1 and size of task list";
        tasks.get(taskIndex).markTaskAsDone();
    }

    /**
     * Marks the specified task in the task list as not done.
     * @param taskIndex The zero-based index of the task.
     * @throws AssertionError If taskIndex is invalid.
     */
    public void markAsNotDone(int taskIndex) throws AssertionError {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "index should be between 1 and size of task list";
        tasks.get(taskIndex).markTaskAsUndone();
    }

    /**
     * Finds tasks in the task list containing the keyword.
     *
     * @param keyword The keyword of the task to be found.
     * @return TaskList containing tasks that contain the specified keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            String lowercaseTask = tasks.get(i).toString().toLowerCase();
            if (lowercaseTask.contains(keyword.toLowerCase())) {
                filteredTasks.addTask(tasks.get(i));
            }
        }
        return filteredTasks;
    }

    /**
     * Updates description of a specified task from taskList.
     *
     * @param taskIndex Index of task to be updated.
     * @param taskInformation New description.
     */
    public void updateDescription(int taskIndex, String taskInformation) {
        tasks.get(taskIndex).updateTaskDescription(taskInformation);
    }

    /**
     * Updates date of a specified task from taskList.
     *
     * @param taskIndex Index of task to be updated.
     * @param fieldToUpdate Date field to be updated.
     * @param newDate New date.
     */
    public void updateDate(int taskIndex, String fieldToUpdate, LocalDate newDate) {
        tasks.get(taskIndex).updateDate(fieldToUpdate, newDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("There are no tasks in your list.\n");
        }
        return sb.toString();
    }
}
