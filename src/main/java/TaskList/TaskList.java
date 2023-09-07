package tasklist;

import java.util.ArrayList;

import duke.DukeException;
import task.Task;

/**
 * The `TaskList` class represents a collection of tasks in the BloopBot application.
 * It provides methods to manipulate the task list, such as adding, deleting, marking, and displaying tasks.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new `TaskList` object with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param taskDesc The task to be added.
     * @return A message indicating the result of the add operation.
     */
    public String addTask(Task taskDesc) {
        try {
            if (taskList.size() < 100) {
                taskList.add(taskDesc);
                return "Task added: " + taskDesc.toString();
            } else {
                return "100/100 Task limit reached. Pay to upgrade your account.";
            }
        } catch (Exception e) {
            return "Error adding the task: " + e.getMessage();
        }
    }

    /**
     * Displays the tasks in the task list.
     * If the list is empty, a message indicating no tasks are present is displayed.
     *
     * @return A formatted string containing the task list or a message indicating no tasks.
     */
    public String displayTasks() {
        StringBuilder output = new StringBuilder();
        if (taskList.isEmpty()) {
            output.append("No tasks present in the list");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                output.append(" ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum The index of the task to be deleted.
     * @return The deleted task.
     * @throws DukeException If the task index is invalid.
     */
    public Task deleteTask(int taskNum) throws DukeException {
        if (taskNum >= 0 && taskNum < taskList.size()) {
            Task removedTask = taskList.remove(taskNum);
            return removedTask;
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
