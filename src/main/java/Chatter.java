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
        System.out.println("How can i help you today?");
    }

    /**
     * Add the user's tasks through a Scanner object, if user says bye,
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
            } else if (userInput.startsWith("mark")){
                tasks.markTaskAsDone(Character.getNumericValue(userInput.charAt(5)));
            } else if (userInput.startsWith("unmark")){
                tasks.markTaskAsNotDone(Character.getNumericValue(userInput.charAt(7)));
            } else if (userInput.startsWith("todo")){
                tasks.addTask(new ToDo(userInput.substring(5)));
            } else {
                tasks.addTask(new Task(userInput));
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
