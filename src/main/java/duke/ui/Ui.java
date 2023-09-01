package duke.ui;

import java.util.ArrayList;
import duke.data.task.Task;


public class Ui {
    public Ui() {
    }
    private String dottedLine = "____________________________________________________________\n";

    public void showLine() {
        System.out.println(dottedLine);
    }

    public void greet() {
        System.out.println(dottedLine +
                "Hello! I'm Charlie\n" +
                "What can I do for you?\n" +
                dottedLine);
    }

    public void exitBot() {
        System.out.println(dottedLine +
                "Bye. Hope to see you again soon!\n" +
                dottedLine);
    }

    public void printMarkedMessage(Task task) {
        System.out.println(dottedLine +
                "Nice! I've marked this task as done:\n" +
                task.toString() + "\n" +
                dottedLine);
    }

    public void printUnmarkedMessage(Task task) {
        System.out.println(dottedLine +
                "OK, I've marked this task as not done yet:\n" +
                task.toString() + "\n" +
                dottedLine);
    }

    public void printAddedToListMessage(Task task, int size) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + size + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    public void printDeletedMessage(Task task, int size) {
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" +
                task +
                "\nNow you have " + size + " tasks in the list." +
                "\n____________________________________________________________\n");
    }
    public void invalidTaskNumber() {
        System.out.println(dottedLine +
                "☹ OOPS!!! The task number is invalid.\n" +
                dottedLine);
    }
    public void printBotErrorMsg() {
        System.out.println(dottedLine +
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                "\n" + dottedLine);

    }

    public void printBotMessage(String msg) {
        System.out.println(dottedLine +
                msg +
                "\n" + dottedLine);
    }


    public void printSearchList(ArrayList<Task> arr) {
        System.out.println(dottedLine +
                "Here are the matching tasks in your list:");
        for (Task t : arr) {
            System.out.printf("%d. %s%n", arr.indexOf(t) + 1, t.toString());
        }
        System.out.println(dottedLine);
    }

    public void printList(ArrayList<Task> arr) {
        System.out.println(dottedLine +
                "Here are the tasks in your list:");
        for (Task t : arr) {
            System.out.printf("%d. %s%n", arr.indexOf(t) + 1, t.toString());
        }
        System.out.println(dottedLine);
    }
}
