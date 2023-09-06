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
    static final String LOGO = "        _______ _                _____ \n"
            + "     /\\|__   __| |        /\\    / ____|\n"
            + "    /  \\  | |  | |       /  \\  | (___  \n"
            + "   / /\\ \\ | |  | |      / /\\ \\  \\___ \\ \n"
            + "  / ____ \\| |  | |____ / ____ \\ ____) |\n"
            + " /_/    \\_\\_|  |______/_/    \\_\\_____/ \n";
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;


    /**
     * Constructs a Atlas object
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
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, storage);
        } catch (AtlasException e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        return "Hello, I'm Atlas! What will you like me to do today?";
    }
}
