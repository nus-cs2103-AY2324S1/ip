package duke;

import java.util.ArrayList;

public class Ui {

    public void displayWelcomeMessage() {
        System.out.println("Hello! I'm Sivraj");
        System.out.println("What can I do for you?");
    }
    String dashLine = "----------------------------------------";

    public void displayToDoMessage(ArrayList<Task> taskList) {
        System.out.println(dashLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("         " + taskList.get(taskList.size() - 1));
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayDeadlineMessage(ArrayList<Task> taskList) {
        System.out.println(dashLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.get(taskList.size() - 1));
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayEventMessage(ArrayList<Task> taskList) {
        System.out.println(dashLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.get(taskList.size() - 1));
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayMarkMessage(ArrayList<Task> taskList, int taskIndex) {
        System.out.println(dashLine);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("       " + taskList.get(taskIndex));
        System.out.println(dashLine);
    }

    public void displayUnmarkMessage(ArrayList<Task> taskList, int taskIndex) {
        System.out.println(dashLine);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + taskList.get(taskIndex));
        System.out.println(dashLine);
    }

    public void displayListMessage(ArrayList<Task> taskList) {
        System.out.println(dashLine);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println(dashLine);
    }

    public void displayDeleteMessage(ArrayList<Task> taskList, Task removedTask) {
        System.out.println(dashLine);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removedTask);
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(dashLine);
    }

    public void displayErrorMessage(DukeException e) {
        System.out.println(dashLine);
        System.out.println("    OOPS " + e.getMessage());
        System.out.println(dashLine);
    }

    public void displayByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

