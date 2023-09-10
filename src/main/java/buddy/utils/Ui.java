package buddy.utils;

import java.util.ArrayList;
import java.util.Scanner;

import buddy.Task;
import buddy.TaskList;

public class Ui {
    private final String name = "Task Buddy";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printGreeting() {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        System.out.println(greeting + inquiry);
        // System.out.println("You have the following buddy.tasks:");
    }

    public void printFarewell() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    public void printAddSuccessMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void printDeleteSuccessMessage(Task deletedTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
