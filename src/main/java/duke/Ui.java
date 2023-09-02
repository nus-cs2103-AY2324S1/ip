package duke;

import java.util.Scanner;

public class Ui {

    public static String scanInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    public static void printGreetings() {
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?");
    }

    public static void printBYE() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printEmptyList() {
        System.out.println("There is currently no items in the list");
    }

    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            String task = String.valueOf(taskList.getTask(i));
            System.out.println(i + ". " + task);
        }
    }

    public static void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + task);
    }

    public static void printNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:" + "\n" + task);
    }

    public static void OutofBounds() {
        System.out.println("IndexOutOfBounds");
    }

    public static void NumberFormat() {
        System.out.println("NumberFormatException");
    }

    public static void removeTask(String deleted, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n" + deleted + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void ToDoExcept() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public static void DateFormatExcept() {
        System.out.println("invalid date format");
    }

    public static void deadlineExcept() {
        System.out.println("Wrong Deadline Format!");
    }

    public static void eventExcept() {
        System.out.println("Wrong Event Format!");
    }
}
