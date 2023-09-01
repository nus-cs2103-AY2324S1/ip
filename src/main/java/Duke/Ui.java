package Duke;

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
}
