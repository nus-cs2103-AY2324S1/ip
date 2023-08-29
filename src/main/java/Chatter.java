import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            tasks.addTask(new ToDo(input.substring(5)), false);
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
                    input.substring(deadlineIndex + 4)), false);
        } catch(ChatterException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
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
                    input.substring(endIndex + 4)), false);
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
                tasks.markTaskAsDone(Character.getNumericValue(userInput.charAt(5)), false);
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

    /**
     * Creates new directory and file for data storage if they do not exist.
     *
     * @throws IOException Error thrown is file cannot be created.
     */
    public static void readFile() throws IOException {
        File f = new File("./data/chatter.txt");
        Path path = Paths.get("./data");
        Files.createDirectories(path);
        f.createNewFile();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] input = s.nextLine().split(", ");

            switch (input[0]) {
            case("D"):
                tasks.addTask(new Deadline(input[2], input[3]), true);
                break;
            case("T"):
                tasks.addTask(new ToDo(input[2]), true);
                break;
            default:
                tasks.addTask(new Event(input[2], input[3], input[4]), true);
                break;
            }

            if (Boolean.parseBoolean(input[1])) {
                tasks.markTaskAsDone(tasks.getNumOfTasks(), true);
            }
        }
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        run();
    }
}
