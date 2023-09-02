package duke.util;

import duke.CheeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui(){
        input = new Scanner(System.in);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        System.out.println("________________________________________________________");
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.CheeChat");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void printAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printFind (ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String toPrint = i + ". " + tasks.get(i).toString();
            System.out.println(toPrint);
        }
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void printInvalidMessage() {
        try {
            throw new CheeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (CheeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printList(String list) {
        System.out.println(list);
    }
}
