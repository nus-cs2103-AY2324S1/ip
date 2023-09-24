package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public void stopUserInput() {
        scanner.close();
    }

    public void displayWelcomeMessage() {
        System.out.println("Hello! I'm Sivraj");
        System.out.println("What can I do for you?");
    }
    String dashLine = "----------------------------------------";

    public void displayToDoMessage(TaskList taskList) {
        System.out.println(dashLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("         " + taskList.getTask(taskList.listSize() - 1));
        System.out.println("     Now you have " + taskList.listSize() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayDeadlineMessage(TaskList taskList) {
        System.out.println(dashLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.getTask(taskList.listSize() - 1));
        System.out.println("     Now you have " + taskList.listSize() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayEventMessage(TaskList taskList) {
        System.out.println(dashLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.getTask(taskList.listSize() - 1));
        System.out.println("     Now you have " + taskList.listSize() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayMarkMessage(TaskList taskList, int taskIndex) {
        System.out.println(dashLine);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("       " + taskList.getTask(taskIndex));
        System.out.println(dashLine);
    }

    public void displayUnmarkMessage(TaskList taskList, int taskIndex) {
        System.out.println(dashLine);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + taskList.getTask(taskIndex));
        System.out.println(dashLine);
    }

    public void displayListMessage(TaskList taskList) {
        System.out.println(dashLine);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.listSize(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.getTask(i));
        }
        System.out.println(dashLine);
    }

    public void displayDeleteMessage(TaskList taskList, Task removedTask) {
        System.out.println(dashLine);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removedTask);
        System.out.println("     Now you have " + taskList.listSize() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayErrorMessage(String message) {
        System.out.println(dashLine);
        System.out.println("    OOPS " + message);
        System.out.println(dashLine);
    }

    public void displayByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void displayLoadErrorMessage(String errorMessage) {
        System.out.println("Error loading tasks: " + errorMessage);
    }
}

