import java.util.Scanner;

/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class Chatter {
    private static ListOfTasks tasks = new ListOfTasks();

    /**
     * Prints greeting message.
     */
    private static void greet() {
        System.out.println("-----------------------");
        System.out.println("Hello! I'm Chatter");
        System.out.println("What can I do for you?");
    }

    /**
     * Add the user's commands through a Scanner object, if user says bye,
     * print exit statement, if user says list, prints list of tasks.
     */
    private static void run() {
        Scanner scanner = new Scanner(System.in);
        greet();
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println("-----------------------");
            if (userInput.equals("list")) {
                tasks.listTasks();
            } else {
                tasks.addTask(userInput);
            }
            System.out.println("-----------------------");
            userInput = scanner.nextLine();
        }

        exit();

    }

    /**
     * Prints exit message.
     */
    private static void exit() {
        System.out.println("-----------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        run();
    }
}
