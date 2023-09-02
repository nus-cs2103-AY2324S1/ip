package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that contains all the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Deletes the task with index taskNumber.
     * @param taskNumber the index of task to remove.
     * @throws DukeException if given task does not exist.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < tasks.size()) {
            Task toRemove = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            return toRemove;
        } else {
            throw new DukeException("☹ OOPS!!! The delete command needs to be followed by an existing task number.");
        }
    }

    /**
     * Completes the task with index taskNumber.
     * @param taskNumber the index of task to complete.
     * @throws DukeException if given task does not exist.
     */
    public Task markTask(int taskNumber) throws DukeException {
        // Assumption: "mark" is not allowed as a task name & you can mark already done tasks.
        // Issue: "mark" by itself crashes.
        if (taskNumber < tasks.size()) {
            tasks.get(taskNumber).completeTask();
            return tasks.get(taskNumber);
        } else {
            throw new DukeException("☹ OOPS!!! The mark command needs to be followed by an existing task number.");
        }
    }

    /**
     * Adds the task newTask.
     * @param newTask task to add.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Returns the tasks that match the keyword in the find task command using regex.
     * @param keyword The keyword used in the find command
     * @return The tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            Matcher matcher = pattern.matcher(task.description);
            if (matcher.find()) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
