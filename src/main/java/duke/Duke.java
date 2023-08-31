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
    private static String NAME = "Moira";
    private static boolean IS_RECEIVING_INPUT = false;
    private static TaskList TASK_LIST;
    private static Scanner SCANNER;
    private static Storage STORAGE;
    private static Ui UI;
    private static Parser PARSER;

    public static void main(String[] args) {
        SCANNER = new Scanner(System.in);
        TASK_LIST = new TaskList();
        STORAGE = new Storage(TASK_LIST);
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

    public static void stopReceivingInput() {
        IS_RECEIVING_INPUT = false;
    }

    public static TaskList getTaskList() {
        return TASK_LIST;
    }
}
