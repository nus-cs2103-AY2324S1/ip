package duke.ui;

import java.util.ArrayList;

import duke.data.task.Task;

public class Ui {
    public Ui() {
    }
    public String greet() {
        return "Hello! I'm Charlie\n"
                + "What can I do for you?\n";
    }

    public String exitBot() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String printMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString() + "\n";
    }

    public String printUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task.toString() + "\n";
    }

    public String printAddedToListMessage(Task task, int size) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    public String printDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + size + " tasks in the list.";
    }
    public String invalidTaskNumber() {
        return "☹ OOPS!!! The task number is invalid.\n";
    }
    public String printBotErrorMsg() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String printBotMessage(String msg) {
        return msg;
    }


    public String printSearchList(ArrayList<Task> arr) {
        String ans = "Here are the matching tasks in your list:";
        for (Task t : arr) {
            ans += String.format("%d. %s%n", arr.indexOf(t) + 1, t.toString());
        }
        return ans;
    }

    public String printList(ArrayList<Task> arr) {
        String ans = "Here are the tasks in your list:\n";
        for (Task t : arr) {
            ans += String.format("%d. %s%n", arr.indexOf(t) + 1, t.toString());
        }
        return ans;
    }
}
