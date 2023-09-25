package alcazar;

import alcazar.commands.Command;
import alcazar.exceptions.AlcazarException;

/**
 * Encapsulates the main method where all the functionality begins.
 */
public class Alcazar {
    /** The storage for all the input tasks */
    private Storage storage;
    /** The list of all the passed tasks */
    private TaskList tasks;

    /**
     * Constructs new Alcazar object
     */
    public Alcazar() {
        String filePath = "./src/main/java/data/tasks.txt";
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Changes the data source file path to the passed location.
     * @param filePath File path to the new data source location
     */
    public void reInitialiseStorage(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Generates response for the given user input.
     * @param input The passed user input
     */
    public Response getResponse(String input) {
        Response inputResponse;

        try {
            Command command = Parser.parse(input);
            inputResponse = command.execute(tasks, storage);
        } catch (AlcazarException e) {
            boolean isUserExiting = false;
            return new Response(e.getMessage(), isUserExiting);
        }

        if (inputResponse.isFileChange()) {
            this.reInitialiseStorage(inputResponse.getFilePath());
        }

        return inputResponse;
    }
}
