package cheems;

import java.util.Scanner;

import cheems.functionalities.Parser;
import cheems.functionalities.Storage;
import cheems.functionalities.Tasklist;
import cheems.functionalities.textUi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the main class of the chatbot.
 */
public class Cheems{
    private final static Scanner scanner = new Scanner(System.in);
    private final Storage storage;
    private final Parser parser;
    private final Tasklist tasklist;
    private final textUi ui;
    private static final String DATABASE_PATH = "data.txt";

    public Cheems() {
        ui = new textUi();
        storage = new Storage(DATABASE_PATH);
        tasklist = new Tasklist(storage);
        parser = new Parser(tasklist);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcomeMsg();
        tasklist.loadTaskFromDatabase();
        String input = ui.getInput(scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                parser.parseAndExecute(input);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
            input = ui.getInput(scanner);
        }

        ui.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems();
        cheems.run();
    }

    public String getWelcomeMsg() {
        tasklist.loadTaskFromDatabase();
        String logo = "\n" +
                "         __                                             \n" +
                "        [  |                                            \n" +
                " .---.   | |--.    .---.   .---.   _ .--..--.    .--.   \n" +
                "/ /'`\\]  | .-. |  / /__\\\\ / /__\\\\ [ `.-. .-. |  ( (`\\]  \n" +
                "| \\__.   | | | |  | \\__., | \\__.,  | | | | | |   `'.'.  \n" +
                "'.___.' [___]|__]  '.__.'  '.__.' [___||__||__] [\\__) ) \n" +
                "                                                        \n";
        String hello = "Heyo I'm Cheems! Nice to meet you:)" + "\n" + "Want to get some fries on the pier together?";

        String msg = "Hello from" + logo + "\n" + hello;
        return msg;
    }


    public String getResponse(String input) {
        try {
            if (!input.equals("bye")) {
                return parser.parseAndExecute(input);
            }
            return "Okay bye:( Let's get the fries next time.";
        } catch (RuntimeException e) {
            return e.toString();
        }

    }
}
