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


    public static void main(String[] args) {
        Path filePath = Paths.get(".", "data", "remy.ser");
        new Remy(filePath).run();
    }

}
