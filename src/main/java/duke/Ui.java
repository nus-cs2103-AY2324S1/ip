package duke;

import duke.task.Task;

import java.util.List;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Axela");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println("Error loading tasks from file.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }
}