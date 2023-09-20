import java.io.FileNotFoundException;
import java.io.IOException;

import ari.Storage;
import ari.TaskList;
import ari.Ui;
import ari.AriException;
import ari.Parser;

import commands.Command;

/**
 * Duke class that runs the chatbot.
 */
public class Ari {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ari(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }
    public String getResponse(String input) throws AriException, IOException {
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

    /**
     * Runs the chatbot.
     * @throws AriException if command is not formatted correctly
     * @throws IOException if file cannot be accessed
     */
    public void run() throws AriException, IOException {
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

    public static void main(String[] args) throws AriException {
        try {
            new Ari("data/ari.txt").run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}