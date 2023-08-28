package duke;
import java.util.ArrayList;

import duke.task.Task;

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

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskNum) {
        Task deletedTask = this.taskList.remove(taskNum);
        return deletedTask;
    }

    public Task getTask(int taskNum) {
        return this.taskList.get(taskNum);
    }

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

    /**
     * Prints every task in the task list that contains the keyword.
     * 
     * @param keyword the keyword that each task must contain
     */
    public void displayListWithKeyword(String keyword) {
        int n = this.getSize();
        int count = 0;
        
        this.ui.showLine();
        this.ui.formatString(" Here are the matching tasks in your list:");

        for (int i = 0; i < n; i += 1) {
            Task currentTask = this.getTask(i);
            String taskString = currentTask.toString();
            if (taskString.contains(keyword)) {
                String output = " " + (count + 1) + ". " + currentTask.toString();
                ui.formatString(output);
                count += 1;
            }
        }

        this.ui.showLine();
    }

    /*
     * Method that prints every task in the task list.
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
