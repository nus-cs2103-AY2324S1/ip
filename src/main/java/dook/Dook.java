package dook;

import dook.command.Command;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Class containing the main execution function.
 */
public class Dook {
    public static final Path PROPER_PATH = Paths.get("dook.txt");
    private final Storage storage;
    private final Parser parser;
    private final UiDisplay uiDisplay;

    public TaskList taskList = new TaskList(null);

    public Dook(Path filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.uiDisplay = new UiDisplay();
    }

    public static void main(String[] args) {
        Dook dook = new Dook(PROPER_PATH);
        dook.run();
    }
    private void run() {
        uiDisplay.greetUser();
        readSavedList();

        boolean isExit = false;
        while (!isExit) {
            String input = uiDisplay.readCommand();
            try {
                Command c = parser.parseFullInput(input);
                isExit = c.isExit;
                c.execute(storage, uiDisplay, taskList);
            } catch (DookException e) {
                uiDisplay.printMessage(e.getMessage());
            }
        }
    }
    private void readSavedList() {
        try {
            taskList = new TaskList(storage.load());
        }  catch (DookException d) {
            uiDisplay.printMessage(d.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }
}
