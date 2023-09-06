package duke;

import storage.Storage;
import taskutil.TaskList;

/**
 * Class to initialise and run the chatbot with GUI.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for a chatbot instance.
     */
    public Duke() {
        storage = new Storage("./data");
        taskList = new TaskList();
    }

    /**
     * Loads data for current instance of chatbot from data file.
     */
    public String loadData() {
        return storage.loadFromFile(taskList);
    }

    /**
     * Parses user inputs for appropriate response.
     *
     * @param input User input.
     * @return Chatbot response to be displayed on GUI.
     */
    public String getResponse(String input) {
        Parser.parseCommand(input, taskList);
        storage.writeToFile(taskList);
        return Ui.getOutMessage();
    }

}
