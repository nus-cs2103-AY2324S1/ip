package phi;

import javafx.fxml.FXML;
import phi.util.Parser;
import phi.util.Storage;
import phi.util.TaskList;


/**
 * Represents the core of the PHI chatbot
 *
 * @author phiphi-tan
 */
public class Phi  {
    private final Storage taskStorage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructor for a new instance of Phi
     * Storage is loaded with the hardcoded file path of the .txt file
     */
    public Phi() {
        taskStorage = new Storage("./data/tasklist.txt");
        tasks = taskStorage.readFromFile();
        this.parser = new Parser(tasks);
    }
  

    public String getStorageMsg() {
        return this.taskStorage.getMsg();
    }

    /**
     * Generates response to user input, and writes data to file after
     */
    @FXML
    public String getResponse(String input) {
        String response = this.parser.handle(input.trim());
        taskStorage.writeToFile(tasks);
        return response;
    }

}

