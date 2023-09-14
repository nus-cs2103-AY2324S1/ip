package oscar;

import oscar.command.Command;
import oscar.essential.InfoList;
import oscar.essential.Parser;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Chatbot named Oscar that can respond to user input.
 */
public class Oscar {
    static final String FILE_PATH = "./data/infolist";

    private final Storage storage;
    private InfoList infos;

    /**
     * Instantiates Oscar with saved data.
     *
     * @param filePath Location of saved info list.
     */
    public Oscar(String filePath) {
        storage = new Storage(filePath);
        try {
            infos = new InfoList(storage.load());
        } catch (OscarException e) {
            infos = new InfoList();
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
            return c.execute(infos, storage);
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
