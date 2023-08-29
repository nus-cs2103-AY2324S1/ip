package dook;

import dook.command.Command;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Dook {
    public static final String FILEPATH = "./data/dook.txt";
    private final Storage storage;
    private final Parser parser;
    private final UiDisplay uiDisplay;

    public TaskList taskList = new TaskList(null);

    public Dook(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.uiDisplay = new UiDisplay();
    }

    public static void main(String[] args) {
        Dook dook = new Dook(FILEPATH);
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
                c.execute(storage, uiDisplay, taskList);
                isExit = c.isExit;
            } catch (DookException e) {
                uiDisplay.printMessage(e.getMessage());
            }

        }
    }
    private void readSavedList() {
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            uiDisplay.printMessage("Failed to load file from text.");
            taskList = new TaskList(new ArrayList<>());
        } catch (DookException d) {
            uiDisplay.printMessage(d.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }
}
