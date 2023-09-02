package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

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
        encodedTaskList.forEach(encodedTask -> tasks.add(Task.decode(encodedTask)));
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
