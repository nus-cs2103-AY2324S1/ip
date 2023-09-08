package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {}

    public void introMessage() {
        System.out.println("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM");
        System.out.println("What can I do for you?");
    }

    public void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void markedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void invalidMark() {
        System.out.println("Enter a valid number to mark");
    }
    public void addedMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
    }

    public void listMessage(ArrayList<Task> list)  {
        System.out.println("Now you have "  + list.size() + " tasks in the list.");
    }

    public void removedMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
    }

    public void validNumberMessage() {
        System.out.println("Enter a valid number to mark");
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
