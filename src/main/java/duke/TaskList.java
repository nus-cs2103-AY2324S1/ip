package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


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
        ui.formatPrintMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + this.tasks.size() + " task(s) in the list.");

    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int taskNumber, Storage storage, Ui ui) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            ui.formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(task);
        storage.deleteTask(taskNumber);
        ui.formatPrintMessage("Noted. I've removed this task:\n  " + task + "\nNow you have " + this.tasks.size() + " task(s) in the list.");
    }

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

    public void markTaskAsDone(int taskNumber, Storage storage, Ui ui) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            ui.formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone(true);
        storage.modifyTask(taskNumber, task);
    }

    public void unmarkTaskAsDone(int taskNumber, Storage storage, Ui ui) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            ui.formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        task.unmarkAsDone(true);
        storage.modifyTask(taskNumber, task);
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

}
