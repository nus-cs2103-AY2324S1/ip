package jarvis.ui;

import jarvis.task.Task;
import jarvis.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void display(String... text) {
        System.out.println("____________________________________________________________");
        for (String i : text)
            System.out.println(i);
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        display("Hello Master! I'm Jarvis, your AI personal assistant.", "What can I do for you?");
    }

    public void farewell() {
        display("Bye. Hope to see you again soon!");
    }

    public void displayAddedTask(Task task, TaskList tasks) {
        display("Got it. I've added this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public void displayDeletedTask(Task task, TaskList tasks) {
        display("Got it. I've removed this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public void displayMarkedTask(Task task) {
        display("Nice! I've marked this task as done:", task.toString());
    }

    public void displayUnmarkedTask(Task task) {
        display("OK, I've marked this task as not done yet:", task.toString());
    }

    public void displayEmptyList() {
        display("You currently have no tasks in your list.");
    }


    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void displayList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
