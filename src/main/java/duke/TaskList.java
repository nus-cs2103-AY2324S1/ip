package duke;

import duke.exception.TaskNotFoundException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Outputs all the tasks currently in the task list.
     */
    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks :(");
            return;
        }

        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, taskList.get(i));
        }
    }

    /**
     * Marks a tasks as done.
     *
     * @param taskId Task ID of the task.
     * @throws TaskNotFoundException If task does not exist.
     */
    public void markTask(int taskId) throws TaskNotFoundException {
        Task doneTask = getTask(taskId);
        doneTask.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
    }

    /**
     * Marks a tasks as not done.
     *
     * @param taskId Task ID of the task.
     * @throws TaskNotFoundException If task does not exist.
     */
    public void unmarkTask(int taskId) throws TaskNotFoundException {
        Task unmarkTask = getTask(taskId);
        unmarkTask.unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkTask);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
        assert !taskList.isEmpty();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskId Task ID of the task.
     * @throws TaskNotFoundException If task does not exist.
     */
    public void deleteTask(int taskId) throws TaskNotFoundException {
        if (taskId >= taskList.size()) {
            throw new TaskNotFoundException();
        }
        Task removedTask = taskList.remove(taskId);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Write list of tasks to file.
     */
    public void findTask(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        taskList.forEach((task -> {
            if (task.getDescription().contains(query) && !matchingTasks.contains(task)) {
                matchingTasks.add(task);
            }
        }));


        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, matchingTasks.get(i));
        }
    }

    /**
     * Write list of tasks to file.
     */
    public void write() {
        Storage.writeToFile(taskList);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int getNumTasks() {
        return taskList.size();
    }

    /**
     * Returns a task in the task list given the ID of the task.
     *
     * @param taskId Task ID of the task.
     * @return A task.
     * @throws TaskNotFoundException If task does not exist.
     */
    public Task getTask(int taskId) throws TaskNotFoundException {
        if (taskId >= taskList.size()) {
            throw new TaskNotFoundException();
        }
        return taskList.get(taskId);
    }
}
