package Iris;

import java.util.Scanner;

/**
 * The main class for the Iris task management application.
 */
public class Iris {
    private final Storage taskStorage;
    private final Parser commandParser;
    private static ToDoList toDoList;

    /**
     * Constructor for the Iris class.
     *
     * @param filePath The file path for storing task data.
     */
    public Iris(String filePath) {
        commandParser = new Parser();
        taskStorage = new Storage(filePath);
        toDoList = new ToDoList(taskStorage.loadTask());
    }

    /**
     * The main method to start and run the Iris application.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Iris("iris.txt").run();
    }

    /**
     * Runs the Iris application.
     */
    public void run() {
        Ui.welcomeMsg();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                commandParser.parseCommand(taskStorage, toDoList, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of Bounds Error: " + e.getMessage());
            }
        }
    }
}
