package atlas;

import atlas.commands.Command;
import atlas.components.Parser;
import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.exceptions.AtlasException;

/**
 * Chatbot implementation
 */
public class Atlas {
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;


    /**
     * Constructs an Atlas object
     * @param fileDir Relative path to save task list
     * @param fileName Name of file to save task list
     */
    public Atlas(String fileDir, String fileName) {
        storage = new Storage(fileDir, fileName);
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Returns Atlas's responses based on user input
     * @param input Command entered by user
     * @return Atlas's responses as a string
     * @throws AtlasException Thrown if exception encountered while executing a command
     */
    public String getResponse(String input) throws AtlasException {
        Command c = parser.parse(input);
        return c.execute(taskList, storage);
    }

    public String getWelcome() {
        return "Who breaks my chains of torment? I am Atlas, leader of the Titans. "
                + "You, mortal, what do you want?";
    }
}
