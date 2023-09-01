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
    private static String NAME = "Moira";
    /** Whether the chatbot is currently accepting user input */
    private static boolean IS_RECEIVING_INPUT = false;
    private static TaskList TASK_LIST;
    private static Scanner SCANNER;
    private static Storage STORAGE;
    private static Ui UI;
    private static Parser PARSER;
    private static String filePath = "./data/duke.txt";

    /**
     * The main method.
     * Initializes chatbot components and runs the main program.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        SCANNER = new Scanner(System.in);
        TASK_LIST = new TaskList();
        STORAGE = new Storage(TASK_LIST, filePath);
        UI = new Ui(NAME);
        PARSER = new Parser(UI);
        run();
    }

    private static void run() {
        IS_RECEIVING_INPUT = true;
        UI.playGreeting();
        while (IS_RECEIVING_INPUT) {
            String userInput = SCANNER.nextLine();
            PARSER.parse(userInput);
        }
        exit();
        SCANNER.close();
    }

    private static void exit() {
        IS_RECEIVING_INPUT = false;
        STORAGE.saveData();
        UI.playGoodbye();
    }

    /**
     * Stops the chatbot from receiving user input.
     */
    public static void stopReceivingInput() {
        IS_RECEIVING_INPUT = false;
    }

    /**
     * Returns the TaskList stored by the chatbot.
     *
     * @return The TaskList stored by the chatbot.
     * @see TaskList
     */
    public static TaskList getTaskList() {
        return TASK_LIST;
    }

    /**
     * Changes the path of the file storing TaskList data to the specified path.
     * This method should only be used for testing purposes.
     *
     * @param path The intended path of the data file as a string.
     */
    public static void changeFilePath(String path) {
        filePath = path;
    }

    // for testing purposes only
    public static boolean getIsReceivingInput() {
        return IS_RECEIVING_INPUT;
    }

    // for testing purposes only
    public static void setIsReceivingInput(boolean isReceivingInput) {
        IS_RECEIVING_INPUT = isReceivingInput;
    }
}
