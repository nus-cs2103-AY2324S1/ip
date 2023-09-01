package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Personal assistant chatbot that can help you manage a to-do list.
 * @author Wu Jingya
 */
public class Duke {
    private static String name = "Moira";
    private static boolean isReceivingInput = false;
    private static TaskList tasks;
    private static Scanner scanner;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static String filePath = "./data/duke.txt";

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

    public static void stopReceivingInput() {
        isReceivingInput = false;
    }

    public static TaskList getTaskList() {
        return tasks;
    }

    //For testing purposes
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
