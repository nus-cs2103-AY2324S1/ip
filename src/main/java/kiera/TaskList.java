package kiera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.tasktype.TaskType;

/**
 * List of tasks added.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Add a task to the list of tasks.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param t Task to be removed.
     */
    public void remove(Task t) {
        this.tasks.remove(t);
    }

    /**
     * Retrieves a task from list by its index.
     *
     * @param i Index of the task to be retrieved.
     * @return Task at the specified index.
     */
    public Task getTaskByIndex(int i) {
        return this.tasks.get(i - 1);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets index of a task in the list of tasks.
     *
     * @param task Task to find the index of.
     * @return Index of task, if indexes in list started at 1.
     */
    public int indexOf(Task task) {
        return this.tasks.indexOf(task) + 1;
    }

    /**
     * Filters tasks in the list of tasks by their type and date.
     *
     * @param t Type of tasks to filter.
     * @param d Date to filter by.
     * @return A list of tasks of the specified type that match the specified date.
     */
    public ArrayList<Task> filterByDate(TaskType t, LocalDate d) {
        switch (t) {
        case DEADLINE:
            return this.tasks.stream()
                    .filter(task -> task.getDeadline() != null)
                    .filter(task -> task instanceof Deadline)
                    .filter(task -> task.getDeadline().equals(d))
                    .collect(Collectors.toCollection(ArrayList::new));
        case EVENT:
            return this.tasks.stream()
                    .filter(task -> task.getStartDate() != null)
                    .filter(task -> task instanceof Event)
                    .filter(task -> task.getStartDate().equals(d))
                    .collect(Collectors.toCollection(ArrayList::new));
        default:
            System.out.println("date does not exist on task type!");
        }
        return new ArrayList<>();
    }

    /**
     * FIlters tasks in the list of tasks by a keyword.
     *
     * @param desc Keyword to filter by.
     * @return A list of tasks containing the specified keyword.
     */
    public ArrayList<Task> filterByKeyword(String desc) {
        return this.tasks.stream()
                .filter(task -> task.toString().contains(desc))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return True if the list of tasks is empty, otherwise return false.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
