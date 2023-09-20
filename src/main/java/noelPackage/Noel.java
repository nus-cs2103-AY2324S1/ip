package noelPackage;

import noelPackage.exceptions.NoelException;
import noelPackage.helper.Parser;
import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;
import noelPackage.helper.Ui;

/**
 * Noel is a chat application that helps manage tasks.
 */
public class Noel {

    private Tasklist tasks;
    private final Parser parser;
    public static boolean updateFromFile = false;

    /**
     * Constructor for the Noel class.
     * Initializes the UI, loads tasks from storage, and sets up the parser.
     */
    public Noel() {

        Storage storage = new Storage("./data/noel.txt");
        try {
            System.out.println("Loading input from file...");
            updateFromFile = true;
            tasks = new Tasklist(storage.loadFile());
            System.out.println("File successfully loaded!");

        } catch (NoelException e) {
            System.out.println("Error loading file!");
            tasks = new Tasklist();

        } finally {
            storage.updateFile();
            parser = new Parser(tasks, storage);
            updateFromFile = false;
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        int commandOption = parser.parseCommand(input);
        return "okay";
    }

}
