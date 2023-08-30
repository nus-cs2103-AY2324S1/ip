package duke;

import java.util.Scanner;
public class Ui {

    public Ui() {
    }

    public static void printLine() {
        System.out.println("\t_______________________________________________________________________");
    }

    public void welcome() {
        printLine();
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public void bye() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        System.out.println("\tHere are the task in your list:");
        tasks.printTasks();
        printLine();
    }

    public void printAddedTask(int size, Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLine();

    }

    public void printAfterMark(int index, TaskList tasks) {
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.getTasks().get(index).toString());
        printLine();
    }

    public void printAfterUnmark(int index, TaskList tasks) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.getTasks().get(index).toString());
        printLine();
    }

    public void printAfterDelete(int index, int size, Task removedTask) {
        printLine();
        System.out.println("\t  " + removedTask.toString());
        System.out.println("\tNow you have " + size + " tasks in the list.");

    }
}
