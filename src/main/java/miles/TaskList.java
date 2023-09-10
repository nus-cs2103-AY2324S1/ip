package miles;

import java.util.ArrayList;

import miles.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Constructor to create a new task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Constructor to create a new task list when given an existing array list.
     * 
     * @param taskList
     */
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
     * @return the deleted task
     * @throws MilesException when task number does not exist
     */
    public Task deleteTask(int taskNum) throws MilesException {
        assert taskNum >= 0 && taskNum < this.taskList.size()
                : "Task number should be above 0 and below the size of the task list.";
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            throw new MilesException("No such task to delete!");
        }

        Task deletedTask = this.taskList.remove(taskNum);
        return deletedTask;
    }

    /**
     * Gets a task from a task list when given the task number.
     * 
     * @param taskNum the task number to be retrieved
     * @return the task
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

    /**
     * Updates the task status when reading from a file.
     * 
     * @param task   the particular task
     * @param status the status when reading from the file
     */
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
        assert taskNum > 0 && taskNum <= this.taskList.size()
                : "Task number should be above 0 and below the size of the task list.";
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            ui.printInvalidTaskNumber(taskNum);
            return;
        }

        Task task = this.getTask(taskNum - 1);
        task.markAsDone();
        this.ui.formatString("Wow, productive day huh! I've marked this task as done:");
        this.ui.formatString(task.toString());
    }

    /**
     * Marks a particular task as undone when given the task number.
     * 
     * @param taskNum the task number to be marked as undone
     */
    public void markTaskAsUndone(int taskNum) {
        assert taskNum > 0 && taskNum <= this.taskList.size()
                : "Task number should be above 0 and below the size of the task list.";
        if (taskNum <= 0 || taskNum > taskList.size()) {
            ui.printInvalidTaskNumber(taskNum);
            return;
        }

        Task task = this.getTask(taskNum - 1);
        task.markAsUndone();
        this.ui.formatString("No worries, I've marked this task as not done yet:");
        this.ui.formatString(task.toString());
    }

    /**
     * Prints every task in the task list that contains the keyword.
     * 
     * @param keyword the keyword that each task must contain
     */
    public void displayListWithKeyword(String keyword) {
        int n = this.getSize();
        int count = 0;

        this.ui.formatString("Here are the matching tasks you requested for, brother:");

        for (int i = 0; i < n; i += 1) {
            Task currentTask = this.getTask(i);
            String taskString = currentTask.toString();

            if (!taskString.contains(keyword)) {
                continue;
            }

            String output = (count + 1) + ". " + taskString;
            ui.formatString(output);
            count += 1;
        }
    }

    /**
     * Prints every task in the task list.
     */
    public void displayList() {
        int n = this.getSize();

        this.ui.formatString("Here are the tasks in your list my G:");
        for (int i = 0; i < n; i += 1) {
            Task currentTask = taskList.get(i);
            String output = (i + 1) + ". " + currentTask.toString();
            ui.formatString(output);
        }
    }
}
