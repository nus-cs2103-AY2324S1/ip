package cr7;

import commands.Command;
import functions.Parser;
import functions.Storage;
import functions.TaskList;
import functions.Ui;
import utilities.CR7Exception;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for running the CR7.CR7 task management application.
 */
public class CR7 {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a CR7.CR7 object with the provided file path for data storage.
     *
     * @param filePath The file path for storing task data.
     */
    public CR7(String filePath) throws IOException {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty!";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFiles());
            assert tasks != null : "Tasks should be initialized!";
        } catch (FileNotFoundException e) {
            ui.showErrorMsg("Error: Task data file not found.");
            tasks = new TaskList();
        }
    }

    /**
     * Creates a CR7.CR7 object to start the application.
     *
     * @param args The command-line arguments (not used in this context).
     */
    public static void main(String[] args) throws IOException {
        new CR7("src/main/data/cr7.CR7.txt");
    }

    /**
     * Retrieves a response based on the given input command.
     *
     * @param input A non-null and non-empty string representing a user command.
     * @return A string containing a prefixed statement followed
     *         by the response of the executed command. If an error occurs while processing
     *         the command, an error message is returned.
     * @throws AssertionError if the input is null or empty.
     * @throws IOException if any IO-related error occurs during command execution.
     */
    public String getResponse(String input) throws CR7Exception {
        try {
            assert input != null && !input.trim().isEmpty() : "Input should not be null or empty!";
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return "Cristiano Ronaldo will always be the GOAT. And here is why: \n" + response;
        } catch (IOException e) {
            return ui.showErrorMsg("An error occurred while processing the command.");
        }
    }
}
