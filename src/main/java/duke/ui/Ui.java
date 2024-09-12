package duke.ui;

import java.util.ArrayList;

import duke.data.task.Task;

public class Ui {
    public Ui() {
    }
    public String greet() {
        return "Good day! I'm Charlie\n"
                + "What can I do for you today?\n";
    }

    public String exitBot() {
        return "Goodbye! Let's hang out again soon!\n";
    }

    public String printMarkedMessage(Task task) {
        return "Good job! I've marked this task as done:\n"
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
        return "What a shame. I've removed this task:\n"
                + task
                + "\nNow you have " + size + " tasks in the list.";
    }
    public String invalidTaskNumber() {
        return "☹ OOPS!! You must've given me a wrong task number.\n";
    }
    public String printBotErrorMsg() {
        return "Drats! I don't know what that means ☹";
    }

    public String printBotMessage(String msg) {
        return msg;
    }


    public String printSearchList(ArrayList<Task> arr) {
        String ans = "Here are the matching tasks in your list:\n";
        for (Task t : arr) {
            ans += String.format("%d. %s%n", arr.indexOf(t) + 1, t.toString());
        }
        return ans;
    }

    public String printSchedule(ArrayList<Task> arr, String date) {
        String ans = String.format("Here is your schedule on: %s\n", date);
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
