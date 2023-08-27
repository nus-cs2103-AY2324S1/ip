package duke;

import java.util.ArrayList;
import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task into a task list.
     * 
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from a task list.
     * 
     * @param taskNum the task number to be deleted
     * @return        the deleted task
     */
    public Task deleteTask(int taskNum) {
        Task deletedTask = this.taskList.remove(taskNum);
        return deletedTask;
    }

    /**
     * Gets a task from a task list when given the task number.
     * 
     * @param taskNum the task number to be retrieved
     * @return        the task
     */
    public Task getTask(int taskNum) {
        return this.taskList.get(taskNum);
    }

    /**
     * Gets the number of tasks in the list.
     * 
     * @return the number of tasks in the list
     */
    public int getSize() {
        return this.taskList.size();
    }

    public void updateTaskStatusFromFile(Task task, String status) {
        if (status.equals("[X]")) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
    }

    /**
     * Marks a particular task as done when given the task number.
     * 
     * @param taskNum the task number to be marked as done
     */
    public void markTaskAsDone(int taskNum) {
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            DukeExceptionHandler.handleTaskNumOutOfBounds(taskNum);
            return;
        }

        Task task = this.getTask(taskNum - 1);
        task.markAsDone();
        this.ui.showLine();
        this.ui.formatString(" Nice! I've marked this task as done:");
        this.ui.formatString("  " + task.toString());
        this.ui.showLine();
    }

    /**
     * Marks a particular task as undone when given the task number.
     * 
     * @param taskNum the task number to be marked as undone
     */
    public void markTaskAsUndone(int taskNum) {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            DukeExceptionHandler.handleTaskNumOutOfBounds(taskNum);
            return;
        }

        Task task = this.getTask(taskNum - 1);
        task.markAsUndone();
        this.ui.showLine();
        this.ui.formatString(" OK, I've marked this task as not done yet:");
        this.ui.formatString("  " + task.toString());
        this.ui.showLine();
    }

    /*
     * Prints every task in the task list.
     */
    public void displayList() {
        int n = this.getSize();

        this.ui.showLine();
        this.ui.formatString(" Here are the tasks in your list:");
        for (int i = 0; i < n; i += 1) {
            Task currentTask = taskList.get(i);
            String output = " " + (i + 1) + ". " + currentTask.toString();
            ui.formatString(output);
        }
        this.ui.showLine();
    }
}
