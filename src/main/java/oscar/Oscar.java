package oscar;

import oscar.command.Command;
import oscar.essential.ItemList;
import oscar.essential.Parser;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Chatbot named Oscar that can respond to user input.
 */
public class Oscar {
    static final String FILE_PATH = "./data/infolist";

    private final Storage storage;
    private ItemList infos;

    /**
     * Instantiates Oscar with saved data.
     *
     * @param filePath Location of saved info list.
     */
    public Oscar(String filePath) {
        storage = new Storage(filePath);
        try {
            infos = new ItemList(storage.load());
        } catch (OscarException e) {
            infos = new ItemList();
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
                + "Oscar can manage your infos such as task and notes!\n"
                + "What can Oscar do for you today?\n";
    }
}
