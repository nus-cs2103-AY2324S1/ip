package chat;

import chat.commands.Command;
import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Parser;
import chat.utils.Storage;
import chat.utils.Ui;
import chat.utils.Ui.ChatWrapper;

/**
 * Contains the chat application, initializes storage and returns response to GUI.
 * @author juzzztinsoong
 */
public class Chat {

    private Storage storage;
    private TaskList tasklist;
    private boolean isExit;
    private Ui ui;

    /**
     * Constructor method for Chat.
     * @param filepath chat.txt filepath.
     */
    public Chat(String filepath) {
        isExit = false;
        storage = new Storage(filepath);
        ui = new Ui();
        try {
            tasklist = storage.loadFromFile();
        } catch (ChatException e) {
            System.err.println("File failed to load");
            tasklist = new TaskList();
        }
    }

    /**
     * Receives and processes user input and returns output to GUI.
     * @param input entered by user.
     * @return output from application.
     */
    public String getResponse(String input) {
        String reply = "";
        try {
            Command c = Parser.parse(input);
            reply = c.execute(tasklist, storage);
            if (c.isExit()) {
                isExit = true;
            }
        } catch (ChatException e) {
            reply += e.getMessage();
        }
        return reply;
    }

    public ChatWrapper getChatWrapper() {
        return ui.getChatWrapper();
    }

    public boolean hasExited() {
        return isExit;
    }
}
