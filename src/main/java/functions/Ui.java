package functions;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected Scanner scanner;
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line
    }

    public void showWelcome() {
        System.out.println("Hello! I'm CR7\n" + "What can I do for you?\n");
    }

    public void showTaskMsg(Task t, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + tasks.numOfTasks() + " tasks in the list\n");
    }

    public void showExitMsg() {
        System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
    }

    public void showListMsg(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task x = tasks.get(i - 1);
            System.out.println(i + "." + x.toString());
        }
        System.out.println();
    }

    public void showMatchesMsg(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks matching your description. " +
                    "Please try another search keyword.\n");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task x = tasks.get(i - 1);
                System.out.println(i + "." + x.toString());
            }
            System.out.println();
        }
    }

    public void showDeleteMsg(Task k, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + k.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.\n");
    }

    public void showMarkMsg(Task k) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + k.toString() + "\n");
    }

    public void showUnmarkMsg(Task k) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + k.toString() + "\n");
    }

    public void showErrorMsg(String errorMsg) {
        System.out.println("OOPS!!! I've encountered a problem here!");
        System.out.println(errorMsg);
    }
}
