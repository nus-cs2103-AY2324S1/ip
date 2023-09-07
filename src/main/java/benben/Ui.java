package benben;

import java.util.ArrayList;

public class Ui {
    private static final String LINE ="_______________________________________\n";

    public Ui() {
    }

    public String show(String message) {
        return message;
    }

    public String showExit() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String showLoadingError() {
        return "An error occurred while we are loading your task list! A new list has been generated!";
    }

    public String showWelcome() {
        return "Hello! I'm BenBen.\n" + "What can I do for you?";
    }

    public String showAdd(String msg, int size) {
        return "Got it. I've added this task:\n" + msg + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    public String showRemove(String msg, int size) {
        return "Noted. I've removed this task:\n" + msg + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    public String showUnmark(String msg) {
        return "OK, I've marked this task as not done yet:\n" + "    " + msg;
    }

    public String showMark(String msg) {
        return "OK, I've marked this task as not done yet:\n" + "    " + msg;
    }

    public String showList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Your list is currently empty! Add a new task now!";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\n");
                sb.append((i + 1) + "." + tasks.get(i).toString());

            }
            return sb.toString();
        }
    }

    public String showMatching(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There is no matching tasks! Try another keyword?";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
                sb.append("/n");
                sb.append((i + 1) + "." + tasks.get(i).toString());
            }
            return sb.toString();
        }
    }
}
