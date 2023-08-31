package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n " + logo);


    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

    }
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void displayCompletionMessage(Task task , int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    public void printMessage(String message, Task task) {
        System.out.println("\t" + message + task.toString());
    }

    public void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }


    public void printTaskList(ArrayList<Task> taskList, String message) {
        System.out.println(message);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
        }
    }

    public void printDeleteMessage(Task removedTask, int size){
        System.out.println("Noted. I've removed this task:\n\t" + removedTask);
        System.out.println("Now you have " + size + " tasks in the list.");
    }


    String readCommand() {
        return sc.nextLine();
    }

}
