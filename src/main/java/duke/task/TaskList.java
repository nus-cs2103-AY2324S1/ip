package duke.task;

import duke.utility.Storage;
import duke.utility.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manage tasks within the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Adds a task to the list based on the task description.
     *
     * @param taskDescription The description of the task.
     * @param storage         The storage handler to save the task.
     * @param ui              The user interface for displaying messages.
     */
    public void addTask(String taskDescription, Storage storage, Ui ui) {
        Task task;
        String[] taskInformation = taskDescription.split(" /");
        String taskName = taskInformation[0].trim();
        if (taskInformation.length == 1) {
            task = new ToDo(taskName);
        } else if (taskInformation.length == 2) {
            task = new Deadline(taskName, taskInformation[1].replace("by ", ""));
        } else if (taskInformation.length == 3) {
            task = new Event(taskName, taskInformation[1].replace("from ", ""), taskInformation[2].replace("to ", ""));
        } else {
            ui.formatPrintMessage("Invalid task format");
            return;
        }
        this.tasks.add(task);
        storage.saveTask(task);
        ui.formatPrintMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + this.tasks.size()
                + " task(s) in the list.");

    }

    /**
     * Adds a task directly to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list based on the task number.
     *
     * @param taskNumber The number of the task to be deleted.
     * @param storage    The storage handler to remove the task.
     * @param ui         The user interface for displaying messages.
     */
    public void deleteTask(int taskNumber, Storage storage, Ui ui) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            ui.formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(task);
        storage.deleteTask(taskNumber);
        ui.formatPrintMessage("Noted. I've removed this task:\n  " + task + "\nNow you have " + this.tasks.size()
                + " task(s) in the list.");
    }

    /**
     * Displays all tasks in the list.
     *
     * @param ui The user interface for displaying messages.
     */
    public void showAllTasks(Ui ui) {
        if (this.tasks.size() == 0) {
            ui.formatPrintMessage("You have no tasks in your list.");
            return;
        }

        System.out.println();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }

        System.out.println();
    }

    /**
     * Marks a task as done based on the task number.
     *
     * @param taskNumber The number of the task to be marked as done.
     * @param storage    The storage handler to update the task status.
     * @param ui         The user interface for displaying messages.
     */
    public void markTaskAsDone(int taskNumber, Storage storage, Ui ui) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            ui.formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone(true);
        storage.modifyTask(taskNumber, task);
    }

    /**
     * Marks a task as not done (undone) based on the task number.
     *
     * @param taskNumber The number of the task to be marked as not done.
     * @param storage    The storage handler to update the task status.
     * @param ui         The user interface for displaying messages.
     */
    public void unmarkTaskAsDone(int taskNumber, Storage storage, Ui ui) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            ui.formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        task.unmarkAsDone(true);
        storage.modifyTask(taskNumber, task);
    }

    public void findTasks(String keyword, Ui ui) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            System.out.println();
            ui.formatPrintMessage("No matching tasks found.");
            return;
        }
        System.out.println();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println(i + 1 + "." + foundTasks.get(i));
        }
        System.out.println();
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

}
