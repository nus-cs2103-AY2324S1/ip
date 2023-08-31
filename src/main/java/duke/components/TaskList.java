package duke.components;

import duke.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * List that contains tasks and methods to interact with the tasks
 */
public class TaskList {
    List<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList object
     * @param tasks Destination path of the file to save the list to
     */
    public TaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Marks task as done
     * @param idx 0-based task index
     * @return Task marked as undone
     */
    public Task markTask(int idx) {
        Task selectedTask = tasks.get(idx);
        selectedTask.markAsDone();
        return selectedTask;
    }

    /**
     * Marks task as not done
     * @param idx 0-based task index
     * @return Task marked as undone
     */
    public Task unmarkTask(int idx) {
        Task selectedTask = tasks.get(idx);
        selectedTask.markAsNotDone();
        return selectedTask;
    }

    /**
     * Deletes task from task list
     * @param idx Zero-based index of task to delete
     * @return Task deleted
     */
    public Task deleteTask(int idx) {
        Task taskToDelete = tasks.get(idx);
        tasks.remove(idx);
        return taskToDelete;
    }

    /**
     * Returns string containing the current task count in the task list
     * @return Message containing the current task count
     */
    public String getCountString() {
        return String.format("Now you have %d %s in the list.\n", tasks.size(),
                tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Returns all tasks occurring on a particular date
     * @param date LocalDate object
     * @return List of tasks that occur on date
     */
    public List<Task> getTaskOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isOccurringOnDate(date)) {
                tasksOnDate.add(t);
            }
        }
        return tasksOnDate;
    }

    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }
}
