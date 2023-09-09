package ducky;

import java.util.Scanner;

import ducky.command.Command;
import ducky.util.Parser;

/**
 * Represents a Ducky chatbot instance.
 */
public class Ducky {

    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a Ducky chatbot instance with the specified file path for persistent data.
     * @param filePath File path for persistent data to be saved in.
     */
    public Ducky(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.storage);
        } catch (DuckyException e) {
            return e.getMessage();
        }
    }

    /*
    public void run() {
        this.storage.load(this.taskList);
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = this.ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(userInput);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DuckyException e) {
                this.ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }
    */
}
