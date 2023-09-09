package friday;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Friday application.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a specific task in the list as done.
     *
     * @param taskNumber The index of the task to be marked as done.
     * @return A string representation of the marked task.
     */
    public String mark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.mark();
        return task.toString();
    }

    /**
     * Marks a specific task in the list as not done.
     *
     * @param taskNumber The index of the task to be marked as not done.
     * @return A string representation of the unmarked task.
     */
    public String unmark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.unmark();
        return task.toString();
    }

    /**
     * Deletes a specific task from the list.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return A string message indicating the task has been removed.
     */
    public String delete(int taskNumber) {
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        return "I've removed this task:\n" + task + "\n" + message();
    }

    /**
     * Finds tasks in the list that contain a specific keyword.
     *
     * @param keyWord The keyword to search for.
     * @return A TaskList containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyWord) {
        TaskList result = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task item = taskList.get(i);
            if (item.containsKeyWord(keyWord)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a message indicating the number of tasks in the list.
     *
     * @return A string message showing the count of tasks.
     */
    public String message() {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }
}
