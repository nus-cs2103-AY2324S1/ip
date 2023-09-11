package duke.task;

import java.util.ArrayList;

import duke.utility.Storage;
import duke.utility.Ui;

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
            ui.printMessage("Invalid task format");
            return;
        }
        this.tasks.add(task);
        storage.saveTask(task);
        ui.printMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + this.tasks.size()
                + " task(s) in the list.");

    }

    /**
     * Adds a task directly to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
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
            ui.printMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        assert task != null : "Task should not be null";
        this.tasks.remove(task);
        storage.deleteTask(taskNumber, ui);
        ui.printMessage("Noted. I've removed this task:\n  " + task + "\nNow you have " + this.tasks.size()
                + " task(s) in the list.");
    }

    /**
     * Displays all tasks in the list.
     *
     * @param ui The user interface for displaying messages.
     */
    public void showAllTasks(Ui ui) {
        if (this.tasks.size() == 0) {
            ui.printMessage("You have no tasks in your list.");
            return;
        }

        ui.printMessage("\nHere are the tasks in your list:\n");

        for (int i = 0; i < this.tasks.size(); i++) {
            ui.printMessage( (i+1)  + "." + this.tasks.get(i) + "\n");
        }

        ui.printMessage("\n");
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
            ui.printMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        assert task != null : "Task should not be null";
        task.markAsDone(true, ui);
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
            ui.printMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        assert task != null : "Task should not be null";
        task.unmarkAsDone(true);
        storage.modifyTask(taskNumber, task);
    }

    /**
     * Finds tasks that match a given keyword and displays them.
     *
     * @param keyword The keyword to search for in tasks.
     * @param ui      The user interface for displaying messages.
     */
    public void findTasks(String keyword, Ui ui) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            System.out.println();
            ui.printMessage("No matching tasks found.");
            return;
        }
        ui.printMessage("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            ui.printMessage(i + 1 + "." + foundTasks.get(i));
        }
        ui.printMessage("");
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

}
