package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    String line = "____________________________________________________________";

    public void showWelcomeMessage() {
        System.out.println(line);
        System.out.println(" Hello! I'm Axela");
        System.out.println(" What can I do for you?");
        System.out.println(line);
    }

    public void showGoodbyeMessage() {
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println(line);
        System.out.println("Error loading tasks from file.");
        System.out.println(line);
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(line);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println(line);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(line);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(line);
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println(line);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(line);
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println(line);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(line);
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(line);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(line);
    }

    public void showError(String message) {
        System.out.println(line);
        System.out.println(" " + message);
        System.out.println(line);
    }
}