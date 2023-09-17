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
        } catch (Exception e) {
            e.printStackTrace();
            tasks = new TaskList();
        } finally {
            parser = new Parser(tasks);
            assert tasks != null : "Tasks should be initialized.";
            assert parser != null : "Parser should be initialized.";
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
        assert input != null : "Input to getResponse should not be null.";

        try {
            Command cmd = parser.parse(input);
            assert cmd != null : "Parsed command should not be null.";
            return cmd.execute(); 
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            storage.saveToFile(tasks.getTasks());
        }
    }
}
