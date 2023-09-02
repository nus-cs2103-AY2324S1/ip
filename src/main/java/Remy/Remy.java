package Remy;

import Remy.command.Command;
import Remy.Task.TaskList;
import java.nio.file.Path;
import java.nio.file.Paths;

// CS2103T Website Increment description-reused
// Reused the example code from the website.
public class Remy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Sets up a Remy chatbot for use.
     * @param filePath location to store tasklist after ending each session.
     */
    public Remy(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatbotException e) {
            Ui.printLongSandwich(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Parses user input and executes tasks based on input.
     * Will end session if user types "exit".
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatbotException e) {
                Ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Starts the Remy Chatbot and provides it with the location to store data.
     * @param args
     */
    public static void main(String[] args) {
        Path filePath = Paths.get(".", "data", "remy.ser");
        new Remy(filePath).run();
    }

}
