package atlas;

import atlas.commands.Command;
import atlas.components.Parser;
import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.exceptions.AtlasException;
import atlas.exceptions.BadDateException;
import atlas.exceptions.BadDateTimeException;
import atlas.exceptions.BadFormatException;
import atlas.exceptions.BadIndexException;
import atlas.exceptions.MissingCommandArgsException;
import atlas.exceptions.MissingNameException;
import atlas.exceptions.UnknownCommandException;
import atlas.exceptions.WrongDateOrderException;
import atlas.exceptions.WrongDateTimeOrderException;

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
     * @throws AtlasException Thrown if exception encountered while creating or executing a command
     */
    public String getResponse(String input) throws AtlasException {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, storage);
        } catch (BadDateException | BadDateTimeException | BadFormatException | BadIndexException
                | MissingCommandArgsException | MissingNameException | UnknownCommandException
                | WrongDateOrderException | WrongDateTimeOrderException e) {
            // Exceptions relating to the creation of the Command object
            throw new AtlasException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            // Exceptions relating to the execution of the Command object
            throw new AtlasException("You point at nothingness, mortal");
        }
    }

    public String getWelcome() {
        return "Who breaks my chains of torment? I am Atlas, leader of the Titans. "
                + "You, mortal, what do you want?";
    }
}
