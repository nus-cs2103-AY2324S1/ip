package simon;

import java.util.Scanner;
import simon.task.Task;

public class Ui {

    public void showWelcome() {
        System.out.println(Simon.SPACEN + SimonAscii.toStr());
        System.out.println("Hello! I'm Simon\nWhat can I do for you?\n" + Simon.SPACE);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!" + Simon.NSPACE);
    }

    public void showLoadingError() {
        System.out.println("Data file not found. Starting with an empty task list." + Simon.NSPACE);
    }

    public void showError(String message) {
        System.out.println(message + Simon.NSPACE);
    }

    public void showAddedTask(Task task, int count) {
        System.out.println(Simon.SPACEN + "Got it. I've added this task:\n" + " " +
                task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task") + Simon.NSPACE);
    }

    public void showDeletedTask(Task task, int count) {
        System.out.println("Noted. I've removed this task:\n" + task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task") + Simon.NSPACE);
    }

    public void showMarkedTask(boolean marked, Task task) {
        if (marked) {
            System.out.println("Nice! I've marked this task as done:\n " + task + Simon.NSPACE);
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n " + task + Simon.NSPACE);
        }
    }

    public void showUnknownCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + Simon.NSPACE);
    }

    public void listTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(Simon.SPACE);
    }

    public String readInput(Scanner scanner) {
        return scanner.nextLine();
    }
}
