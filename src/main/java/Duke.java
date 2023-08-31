import java.io.FileNotFoundException;
import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.Parser;

/**
 * Duke class that runs the chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot.
     * @throws DukeException if command is not formatted correctly
     * @throws IOException if file cannot be accessed
     */
    public void run() throws DukeException, IOException {
        ui.welcome();
        while (true) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                ui.bye();
                break;
            }
            Parser.parseAndExecute(command, ui, tasks, storage);
        }

    }

    public static void main(String[] args) throws DukeException {
        try {
            new Duke("data/duke.txt").run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}