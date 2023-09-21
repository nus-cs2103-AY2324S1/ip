package seedu.duke;

/**
 * a duke handles the logic behind the application
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    
    public Duke() {
        String savedString = "";
        storage = new Storage("./data/duke.txt");
        savedString = storage.load();
        tasks = new TaskList(savedString);
    }

    public String getResponse(String input) {
        String response = Command.processUserInput(input, tasks);
        storage.save(tasks);
        return response;
    }

}