import java.util.Scanner;

/**
 * Represents Chatter the chatbot.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
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
        System.out.println("-----------------------");
    }

    /**
     * Adds todo task to the list of tasks.
     *
     * @param input The user input with the description of the task.
     */
    private static void addTodo(String input) {
        try {
            if (input.length() < 6) {
                throw new ChatterException("☹ OOPS!!! The description of a todo cannot be empty!");
            }
            tasks.addTask(new ToDo(input.substring(5)));
        } catch(ChatterException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds deadline task to the list of tasks.
     *
     * @param input The user input with the description of the task.
     */
    private static void addDeadline(String input) {
        try {
            int deadlineIndex = input.indexOf("/by");
            if (deadlineIndex == -1) {
                throw new ChatterException("Please add a '/by' statement with the deadline.");
            }

            tasks.addTask(new Deadline(input.substring(9, deadlineIndex - 1),
                    input.substring(deadlineIndex + 4)));
        } catch(ChatterException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println("Please enter a valid description or deadline.");
        }
    }

    /**
     * Adds event task to the list of tasks.
     *
     * @param input The user input with the description of the task.
     */
    private static void addEvent(String input) {
        try {
            int startIndex = input.indexOf("/from");
            if (startIndex == -1) {
                throw new ChatterException("Please add a '/from' statement with the start time / date.");
            }

            int endIndex = input.indexOf("/to");
            if (endIndex == -1) {
                throw new ChatterException("Please add a '/to' statement with the end time / date.");
            }

            tasks.addTask(new Event(input.substring(6, startIndex - 1),
                    input.substring(startIndex + 6, endIndex - 1),
                    input.substring(endIndex + 4)));
        } catch(ChatterException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println("Please enter a valid description and start / end time.");
        }
    }

    /**
     * Check the user's commands and calls the appropriate methods according
     * to the commands.
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
            } else if (userInput.startsWith("delete")){
                tasks.delete(Character.getNumericValue(userInput.charAt(7)));
            } else if (userInput.startsWith("todo")){
                addTodo(userInput);
            } else if (userInput.startsWith("deadline")){
                addDeadline(userInput);
            } else if (userInput.startsWith("event")){
                addEvent(userInput);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("Please enter a valid command!");
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
