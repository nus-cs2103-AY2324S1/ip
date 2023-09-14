package atlas.components;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import atlas.tasks.Task;

/**
 * List that contains tasks and methods to interact with the tasks
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList object
     * @param tasks Destination path of the file to save the list to
     */
    public TaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Adds a new task into the list
     * @param newTask Task to be added to the list
     */
    public void addTask(Task newTask) {
        assert newTask != null;
        tasks.add(newTask);
    }

    /**
     * Marks tasks as done
     * @param indices One or more 0-based task indices
     * @return Tasks marked as done
     */
    public List<Task> markTasks(int... indices) {
        List<Task> markedTasks = new ArrayList<>();
        for (int i : indices) {
            try {
                Task selectedTask = tasks.get(i);
                selectedTask.markDone();
                markedTasks.add(selectedTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.printf("Invalid index %d\n", i);
            }
        }
        return markedTasks;
    }

    /**
     * Marks task as not done
     * @param indices One or more 0-based task indices
     * @return Tasks marked as undone
     */
    public List<Task> unmarkTasks(int... indices) {
        List<Task> unmarkedTasks = new ArrayList<>();
        for (int i : indices) {
            try {
                Task selectedTask = tasks.get(i);
                selectedTask.markNotDone();
                unmarkedTasks.add(selectedTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.printf("Invalid index %d\n", i);
            }
        }
        return unmarkedTasks;
    }

    /**
     * Deletes task from task list. Deleting multiple tasks at once is not supported as deletion is performed
     * using indices, which might be affected by the deletion of previous indices.
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
        final String taskPlurality = tasks.size() == 1 ? "task" : "tasks";
        return String.format("Now you have %d %s in the list.\n", tasks.size(), taskPlurality);
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

    /**
     * Returns all tasks containing a particular keyword
     * @param keyword Keyword to query for
     * @return List of tasks that contain keyword
     */
    public List<Task> getTasksWithKeyword(String keyword) {
        List<Task> tasksWithKeyword = new ArrayList<>();
        for (Task t : tasks) {
            if (t.hasKeyword(keyword)) {
                tasksWithKeyword.add(t);
            }
        }
        return tasksWithKeyword;
    }

    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }
}
