package sae.util;

import sae.task.Task;

import java.util.ArrayList;

public class Ui {

    public String greetUser() {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");
        return "Hello! I'm Sae\nWhat can I do for you?";

    }

    public String bidGoodbye() {
        return "Bye. Hope to see you again soon!";
        //System.out.println("Bye. Hope to see you again soon!");
    }

    public void unknownInput() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String addTaskMessage(Task task, int taskcount) {
        return "Got it. I've added this task:\n" + "  " + task.toString() +
                "\nNow you have " + taskcount + " task(s) in the list.";
    }

    public String deleteTaskMessage(Task task, int taskcount) {
        return "Noted. I've removed this task:\n"
                + "  " + task.toString() + "\nNow you have " + taskcount + " tasks in the list.";
    }

    public String markAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    public String unMarkAsDoneMessage(Task task) {
        return "I have unmarked this task as done:\n" + task.toString();
    }

    public String groupDeleteMessage(int start, int end, ArrayList<Task> tasks) {
        String frontString = "Task " + (start + 1) + " to Task "
                + (end + 1) +" has been deleted.\n The deleted tasks are :\n";
        StringBuilder backString = new StringBuilder();
        for (Task task : tasks) {
            backString.append(task.toString() + "\n");
        }
        return frontString + backString.toString();
    }
}
