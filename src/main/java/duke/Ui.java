package duke;

import java.util.Scanner;

public class Ui {
    String lnspace = "____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greeting() {
        String greeting = lnspace + "\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + lnspace + "\n";
        System.out.println(greeting);
    }

    public void ending() {
        String ending = "Bye. Hope to see you again soon!\n"
                + lnspace;
        System.out.println(ending);
        this.sc.close();
    }

    public void printLine() {
        System.out.println(lnspace);
    }

    public void showLoadingError() {
        System.out.println("Existing data not found. Creating new data file tasks.txt found in ./data/ folder.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void addTask(TaskList arr) {
        System.out.println("Got it. I've added this task:");
        System.out.println(arr.taskToString(arr.length() - 1));
        System.out.println("Now you have " + (arr.length()) + arr.numTasksToString() + " in the list.");
    }

    public void markTask(int index, TaskList arr) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(arr.taskToString(index));
    }

    public void unmarkTask(int index, TaskList arr) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(arr.taskToString(index));
    }

    public void list(TaskList arr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.length(); i++) {
            System.out.printf("%d. %s\n", i + 1, arr.taskToString(i));
        }
    }

    public void deleteTask(TaskList arr, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(arr.taskToString(index));
        System.out.println("Now you have " + (arr.length() - 1) + arr.numTasksToString() + " in the list.");
    }
}
