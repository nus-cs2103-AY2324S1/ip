package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Personal assistant chatbot that can help you manage a task list.
 * This is the main class of the program.
 *
 * @author Wu Jingya
 */
public class Duke {
    private static String name = "Moira";
    /** Whether the chatbot is currently accepting user input */
    private static boolean isReceivingInput = false;
    private static TaskList tasks;
    private static Scanner scanner;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static String filePath = "./data/duke.txt";

    /**
     * The main method.
     * Initializes chatbot components and runs the main program.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        tasks = new TaskList();
        storage = new Storage(tasks, filePath);
        ui = new Ui(name);
        parser = new Parser(ui);
        run();
    }

    private static void run() {
        isReceivingInput = true;
        ui.playGreeting();
        while (isReceivingInput) {
            String userInput = scanner.nextLine();
            parser.parse(userInput);
        }
        exit();
        scanner.close();
    }

    private static void exit() {
        isReceivingInput = false;
        storage.saveData();
        ui.playGoodbye();
    }

    /**
     * Stops the chatbot from receiving user input.
     */
    public static void stopReceivingInput() {
        isReceivingInput = false;
    }

    /**
     * Returns the TaskList stored by the chatbot.
     *
     * @return The TaskList stored by the chatbot.
     * @see TaskList
     */
    public static TaskList getTaskList() {
        return tasks;
    }

    //For testing purposes
    /**
     * Changes the path of the file storing TaskList data to the specified path.
     * This method should only be used for testing purposes.
     *
     * @param path The intended path of the data file as a string.
     */
    public static void changeFilePath(String path) {
        filePath = path;
    }

    //For testing purposes
    public static boolean getIsReceivingInput() {
        return isReceivingInput;
    }

    //For testing purposes
    public static void setIsReceivingInput(boolean isReceivingInput) {
        Duke.isReceivingInput = isReceivingInput;
    }
}
