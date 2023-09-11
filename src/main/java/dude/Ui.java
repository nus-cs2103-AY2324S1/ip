package dude;

// deals with interactions with the user
import dude.task.Task;

import java.util.Scanner;
public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public String readCommand() {
        return this.sc.nextLine();
    }
    public void showWelcome() {
        String greeting = "Hello, I'm Dude!\n" +
                "What can I do for you?";
        System.out.println(greeting);
    }
    public void showFarewell() {
        String greeting = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
    }
    public void showLine() {
        System.out.println("__________________________________________________");
    }
    public void showTaskList(TaskList taskList) {
        int nTasks = taskList.getSize();
        if (nTasks == 0) {
            System.out.println("You have no saved tasks.");
        } else {
            for (int i = 0; i < nTasks; i++) {
                Task task = taskList.getTask(i);
                System.out.printf("%d. %s\n", i+1, task.toString());
            }
        }
    }
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf(task.toString());
        // task is already done?
    }
    public void showUnmarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as as not done yet:");
        System.out.printf(task.toString());
        // task is already undone?
    }
    public void showDeletedTask(Task task, int nTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", nTasks);
    }
    public void showAddedTask(Task task, int nTasks) {
        System.out.printf("Got it. I've added this task:\n%s\n", task.toString());
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }
    public void showUnknownCommand() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public void showError(String message) {
    }

    public void showLoadingError() {
    }
}
