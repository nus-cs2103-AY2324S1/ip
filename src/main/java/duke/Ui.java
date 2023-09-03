package duke;

import duke.task.Task;

public class Ui {
    public void greetUser() {
        System.out.println("Hello friend :> My name is John, nice to meet you! What do you have to do today?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye for now, hope to see you soon.");
    }

    public void showReply() {
        System.out.print("Reply John: ");
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n  " + task +
                "\nNow you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:\n" + "  " + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + "[X] " + task.getDescription());
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + task.getDescription());
    }

}
