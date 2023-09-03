package ui;

import java.util.Scanner;

import tasklist.TaskList;
import task.Task;

public class Ui {
    private Scanner scanner;
    final String SPACE = "------------------------------------"; // for spacing purposes
    String name = "Adam's Bot"; // name of bot

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();// remove trailing spaces and get use input
    }

    public void showError(String errorMessage) {
        System.out.println(SPACE);
        System.out.println(errorMessage);
        System.out.println(SPACE);
    }

    public void showWelcome() {
        System.out.println(SPACE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(SPACE);
    }

    public void showGoodbye() {
        System.out.println(SPACE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPACE);
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(SPACE);

        // iterate through ArrayList to print tasks
        for (int i = 0; i < taskList.size(); i++) {
            int currentNumber = i + 1;
            System.out.println(currentNumber + ". " + taskList.get(currentNumber).toString());
        }
        System.out.println(SPACE);
    }

    public void showMarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
    }

    public void showUnmarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
    }

    public void showAddText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
    }

    public void showDeleteText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
    }
}

