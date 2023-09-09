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
    private final UserInterface ui;

    /**
     * Constructs a Ducky chatbot instance with the specified file path for persistent data.
     * @param filePath File path for persistent data to be saved in.
     */
    public Ducky(String filePath) {
        Scanner sc = new Scanner(System.in);
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.ui = new UserInterface(sc);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (DuckyException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads persistent cache (if available) and starts the chatbot task queue.
     */
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

    public static void main(String[] args) {
        new Ducky("tasks.txt").run();
    }
}
