package martin;

import java.io.IOException;

import martin.commands.Command;
import martin.parser.Parser;
import martin.task.TaskList;

public class Martin {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Martin(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
            parser = new Parser(tasks);
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Processes the given input and returns a response.
     *
     * @param input User's input string.
     * @return Response message.
     * @throws IOException
     */
    public String getResponse(String input) throws IOException {
        try {
            Command cmd = parser.parse(input);
            return cmd.execute();  // Assuming execute() returns a String
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            storage.saveToFile(tasks.getTasks());
        }
    }
}
