package duke;

import java.util.ArrayList;

public class Ui {
    private static void printSeparationLine() {
        System.out.println("____________________________________________________________");
    }

    public static void welcomeMessage() {
        Ui.printSeparationLine();
        System.out.println(" Hello! I'm Jarvis");
        System.out.println(" What can I do for you?");
        Ui.printSeparationLine();
    }

    public static void listTasks(ArrayList<Task> tasks) {
        Ui.printSeparationLine();
        System.out.println(" Here are the tasks in your list:");
        int counter = 0;
        while (counter != tasks.size()) {
            counter++;
            System.out.println(" " + counter + "." + tasks.get(counter - 1).toString());
        }
        Ui.printSeparationLine();
    }

    public static void markTask(Task t) {
        Ui.printSeparationLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + t.toString());
        Ui.printSeparationLine();
    }

    public static void addTask(Task t, ArrayList<Task> taskList) {
        Ui.printSeparationLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        Ui.printSeparationLine();
    }

    public static void deleteTask(Task t, ArrayList<Task> taskList) {
        Ui.printSeparationLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        Ui.printSeparationLine();
    }

    public static void printException(DukeException e) {
        Ui.printSeparationLine();
        System.out.println(" " + e.getMessage());
        Ui.printSeparationLine();
    }

    public static void farewellMessage() {
        Ui.printSeparationLine();
        System.out.println(" Bye. Hope to see you again soon!");
        Ui.printSeparationLine();
    }
}
