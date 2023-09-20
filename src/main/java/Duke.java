import java.io.FileNotFoundException;
import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.Parser;

import commands.Command;

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
    public String getResponse(String input) throws DukeException, IOException {
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

    /**
     * Runs the chatbot.
     * @throws DukeException if command is not formatted correctly
     * @throws IOException if file cannot be accessed
     */
    public void run() throws DukeException, IOException {
        ui.welcome();
        boolean isLoop = true;
        while (isLoop) {
            String command = ui.readCommand();

            if (command.equals("bye")) {
                isLoop = false;
            }

            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
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