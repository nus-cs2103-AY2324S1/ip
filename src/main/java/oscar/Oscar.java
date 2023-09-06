package oscar;

import oscar.command.Command;
import oscar.essential.Parser;
import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.exception.OscarException;

/**
 * Chatbot named Oscar that can respond to user input.
 */
public class Oscar {
    static final String FILE_PATH = "./data/tasklist";

    private final Storage storage;
    private TaskList tasks;

    /**
     * Instantiates Oscar with saved data.
     *
     * @param filePath Location of saved task list.
     */
    public Oscar(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OscarException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Obtains the response of Oscar based on the given user input.
     *
     * @param input Typed user input
     * @return Response to command of user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (OscarException e) {
            return e.getMessage();
        }
    }

    /**
     * Greets user upon initialisation.
     *
     * @return Greeting message.
     */
    public String greet() {
        return "Hello! This is Oscar, your friendly chatbot :)\n"
                + "What can Oscar do for you?\n";
    }
}
