package pardiyem.driver;

import java.io.IOException;

import pardiyem.parser.Parser;
import pardiyem.storage.Storage;
import pardiyem.task.TaskList;

public class Pardiyem {
    private Storage storage;
    private TaskList tasks;

    public Pardiyem() throws IOException {
        storage = new Storage();
        tasks = storage.load();
    }

    public String getResponse(String input) {
        try {
            return Parser.parseCommand(input).execute(tasks, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
