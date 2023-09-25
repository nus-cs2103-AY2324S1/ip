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
     * Generates response for the given user input.
     */
    public Response getResponse(String input) {
        String filePath = "./src/main/java/data/tasks.txt";
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        Response inputResponse;
        try {
            Command command = Parser.parse(input);
            inputResponse = command.execute(tasks, storage);
        } catch (AlcazarException e) {
            boolean isUserExiting = false;
            return new Response(e.getMessage(), isUserExiting);
        }
        return inputResponse;
    }
}
