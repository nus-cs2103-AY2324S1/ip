package thea;

import java.util.Scanner;

public class Ui {
    private final Scanner input;
    public Ui() {
        this.input = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?");
    }

    public void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }

    public void taskMark(Task task) {
        System.out.printf("Great job! ˊᗜˋ I've marked this task as done:\n  %s\n", task);
    }

    public void taskUnmark(Task task) {
        System.out.printf("Okay, I've marked this task as not done yet:\n  %s\n", task);
    }

    public void printList(TaskList tasks) {
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("Yay! You have no tasks in your list.");
        }
    }

    public void taskAdd(Task task, TaskList tasks) {
        System.out.println("I have added the following task to your list:\n  "
                + task.toString() + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list. You can do this!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readNextLine() {
        return input.nextLine();
    }

    public void taskDelete(Task task, TaskList tasks) {
        System.out.println("I have removed the following task to your list:\n  "
                + task.toString() + "\nNow you have " + (tasks.size() - 1)
                + ((tasks.size() - 1) == 1 ? " task" : " tasks")
                + " in the list.");
    }

    public void relevantTasksFound(TaskList relevantTasks) {
        if (relevantTasks.isEmpty()) {
            System.out.println("No matching task found. Maybe you have finished them?");
        } else {
            System.out.println("Here are the matching tasks on your list:");
            for (int i = 0; i < relevantTasks.size(); i++) {
                System.out.println((i + 1) + ". " + relevantTasks.get(i));
            }
        }
    }
}
