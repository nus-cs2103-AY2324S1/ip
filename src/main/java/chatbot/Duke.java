package chatbot;

import chatbot.commands.Command;
import chatbot.exceptions.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

import java.io.FileNotFoundException;

public class Duke {
    private final Storage storage;
    private final TaskList tasklist;
    private final Ui ui;

    public Duke(String filePath) throws FileNotFoundException {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasklist = storage.readFromFile();
    }

    public void run() {
        boolean isExit = false;
        ui.showWelcome();

        while (!isExit) {
            try {
                Command c = Parser.parse(ui.readInput());
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("./data/tasks.txt").run();
    }
}
