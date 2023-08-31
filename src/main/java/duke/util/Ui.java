package duke.util;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public Ui () {
        this.sc = new Scanner(System.in);
    }

    public String nextCommand() {
        if (!sc.hasNextLine()) {
            return "";
        }
        return sc.nextLine();
    }

    public void printEntryMessage() {
        String entryMessage = HORIZONTAL_LINE
                + "Hello! I'm Chad \n"
                + "What can I do for you? \n"
                + HORIZONTAL_LINE;
        System.out.println(entryMessage);
    }

    public void printExitMessage() {
        String exitMessage = HORIZONTAL_LINE
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;
        System.out.println(exitMessage);
    }

    public void printList(TaskList list) {
        System.out.print(HORIZONTAL_LINE);
        System.out.print(list.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAdd(Task task, int size) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printDelete(Task task, int size) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printMark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printUnmark(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(Exception e) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Oops! we encountered an error");
        System.out.println(e.getMessage());
        System.out.println(HORIZONTAL_LINE);
    }

    public void printCommandNotFound() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Oops! I'm sorry, but I don't know what that means :-(");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printFind(TaskList taskList) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(taskList.toString());
        System.out.println(HORIZONTAL_LINE);
    }
}
