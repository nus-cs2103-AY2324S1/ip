package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.tasks.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialises a TaskList with the given tasks
     *
     * @param tasks the tasks to initialise the list
     */
    public TaskList(Iterable<Task> tasks) {
        this.tasks = new ArrayList<>();
        tasks.forEach(this.tasks::add);
    }

    /**
     * Decodes a list of task from a given encoded task list.
     *
     * @param encodedTaskList the encoded task list as a string
     * @return the decoded task list
     */
    public static TaskList decode(List<String> encodedTaskList) {
        List<Task> tasks = new ArrayList<>();
        encodedTaskList.forEach(encodedTask -> {
            Task task = Task.decode(encodedTask);
            if (!Objects.isNull(task)) {
                tasks.add(task);
            }
        });
        return new TaskList(tasks);
    }

    /**
     * Returns the task with the given task number.
     * We subtract 1 because task numbers start from 1.
     *
     * @param taskNum the task number to get
     * @return the task with the corresponding task number
     */
    public Task get(int taskNum) throws IndexOutOfBoundsException {
        return tasks.get(taskNum - 1);
    }

    /**
     * Adds the given task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes the task with the given task number.
     *
     * @param taskNum the task number to delete
     */
    public void delete(int taskNum) throws IndexOutOfBoundsException {
        tasks.remove(taskNum - 1);
    }

    /**
     * Filters the task list by the given predicate and returns a new TaskList containing the filtered tasks.
     *
     * @param pred the predicate used to filter for tasks
     * @return a new task list containing filtered tasks
     */
    public TaskList filter(Predicate<? super Task> pred) {
        return new TaskList(tasks.stream().filter(pred).collect(Collectors.toList()));
    }

    /**
     * Encodes the task list as a list of strings to be stored in a file.
     *
     * @return the encoded task list as a list of strings
     */
    public List<String> encode() {
        List<String> encodedTaskList = new ArrayList<>();
        tasks.forEach(task -> encodedTaskList.add(task.encode()));
        return encodedTaskList;
    }
}
