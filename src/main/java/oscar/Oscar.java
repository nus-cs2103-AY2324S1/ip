package oscar;

import oscar.command.Command;

import oscar.essential.Parser;
import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.essential.Ui;

import oscar.exception.OscarException;

import java.util.Scanner;

/**
 * Chatbot named Oscar that can respond to user input.
 */
public class Oscar {
    static final String FILE_PATH = "./data/tasklist";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Instantiates Oscar with saved data.
     *
     * @param filePath Location of saved task list.
     */
    public Oscar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OscarException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs Oscar and allows for user input.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        ui.greet();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (OscarException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        scanner.close();
    }

    /**
     * Programme flow to run Oscar.
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Oscar(FILE_PATH).run();
    }
}
