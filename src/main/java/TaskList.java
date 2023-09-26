/**
 * Class representing a list of tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[100];
        taskCount = 0;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param task The index of the task to be deleted from the list.
     */
    public void deleteTask(int task) {
        Task[] newTasks = new Task[100];
        for (int i = 0; i < taskCount - 1; i++) {
            int j = i;
            if (i >= task) j = i + 1;
            newTasks[i] = tasks[j];
        }
        tasks = newTasks;
        taskCount--;
        assert taskCount >= 0;
    }

    public void markTask(int task) {
        tasks[task].markAsDone();
        assert tasks[task].isDone();
    }

    public int getCount() {
        return taskCount;
    }

    public Task getTask(int i) {
        return tasks[i];
    }
}