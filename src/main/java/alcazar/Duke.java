package alcazar;

/**
 * The class with the main method where all the functionality begins.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    /**
     * Method to run all the functionality to drive this project.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Parser p = new Parser();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = p.isExit(fullCommand);
                if (isExit) {
                    continue;
                }
                ui.showLine(); // show the divider line ("_______")
                p.parse(fullCommand, tasks, ui, storage);
            } catch (InvalidTaskException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch (InvalidArgumentException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch(InvalidSerialException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMsg();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke("./src/main/java/data/tasks.txt");
        chatBot.run();
    }
}
