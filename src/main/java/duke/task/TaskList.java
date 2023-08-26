package duke.task;

import duke.Duke;
import duke.Ui;

import java.util.ArrayList;

/**
 * TaskList encapsulates a list of tasks as well as the various task related functions for Duke.
 */
public class TaskList {
    /**
     * List of all tasks added.
     */
    private ArrayList<Task> taskList;
    private Duke dukeBot;

    public TaskList(ArrayList<Task> taskList, Duke dukeBot) {
        this.taskList = taskList;
        this.dukeBot = dukeBot;
    }


    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Ui.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %s tasks in the list.%n", taskList.size());
        Ui.printLine();

        dukeBot.storage.writeToDatabase(taskList);
    }

    /**
     * Marks a task as done.
     *
     * @param i The index of the task in the list (1-based indexing).
     */
    public void markTask(int i) {
        taskList.get(i - 1).markAsDone();
        Ui.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(i - 1).toString());

        dukeBot.storage.writeToDatabase(taskList);
    }

    /**
     * Marks a task as not done.
     *
     * @param i The index of the task in the list (1-based indexing).
     */
    public void unmarkTask(int i) {
        taskList.get(i - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(i - 1).toString());

        dukeBot.storage.writeToDatabase(taskList);
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task in the list (1-based indexing).
     */
    public void deleteTask(int i) {
        Task removedTask = taskList.remove(i - 1);
        Ui.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.printf("Now you have %s tasks in the list.%n", taskList.size());

        dukeBot.storage.writeToDatabase(taskList);
    }

    /**
     * Lists out all the tasks that are in the list.
     */
    public void listTasks() {
        Ui.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%s.%s%n", i + 1, taskList.get(i).toString());
        }
        Ui.printLine();
    }

    /**
     * Prints all tasks with names strictly containing a keyword.
     * @param key The keyword.
     */
    public void findTasks(String key) {
        int count = 1;
        Ui.printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskList) {
            if (task.getTaskName().contains(key)) {
                System.out.printf("%s. %s%n", count++, task);
            }
        }
        Ui.printLine();
    }
}
