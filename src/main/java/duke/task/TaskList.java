package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList represents the list of tasks of the user.
 *
 * @author Andrew Daniel Janong
 */
public class TaskList {
    /** Tasks of the user */
    private List<Task> tasks;

    /**
     * Creates a TaskList object which is initially is empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Reads all tasks from the storage and
     * adds them to the task list.
     *
     * @param tasks Tasks from the storage.
     */
    public void readTasksFromStorage(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the task with a specific index.
     *
     * @param taskIndex Index of the task.
     * @return The task with that index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    /**
     * Gets the size of the current task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks whether an index is valid.
     *
     * @param taskIndex Index to be checked.
     * @return Validity of the index.
     */
    public boolean isValidIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasks.size();
    }

    /**
     * Adds a task to the task list.
     * Sends a message confirming the added task.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        Ui.printLines("Got it. I've added this task:",
                "\t " + newTask,
                "Now you have " + getSize() + " tasks in your list. Good luck!");

    }

    /**
     * Deletes a task from the task list.
     * Sends a message confirming the deleted task.
     *
     * @param taskIndex Task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);

        tasks.remove(taskIndex - 1);

        Ui.printLines("Noted. I've removed this task:",
                deletedTask.toString(),
                "Now you have " + this.tasks.size() + " tasks in your list. Good luck!");
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex Index of the task to be marked.
     */
    public void markTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsDone();

        Ui.printLines("Nice job! I've marked this task as done:",
                "\t " + task);
    }

    /**
     * Unmarks a task.
     *
     * @param taskIndex Index of the task to be unmarked.
     */
    public void unmarkTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsNotDone();

        Ui.printLines("What happened? I've marked this task as not done yet:",
                "\t " + task);
    }

    @Override
    public String toString() {
        String tasksList;

        if (getSize() > 0) {
            tasksList = "Here are the tasks in your list:\n";

            for (int taskIndex = 1; taskIndex <= tasks.size(); taskIndex++) {
                tasksList += ("\t " + taskIndex + "." + getTask(taskIndex) + "\n");
            }

            tasksList += ("\t Keep up the good work!");
        } else {
            tasksList = "You currently have no tasks :)";
        }

        return tasksList;
    }


}
