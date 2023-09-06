package chat;

import chat.commands.Command;
import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Parser;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public class Chat {

    private Storage storage;
    private TaskList tasklist;
    private boolean isExit;

    /**
     * Constructor method for Chat.
     * @param filepath chat.txt filepath.
     */
    public Chat(String filepath) {
        isExit = false;
        storage = new Storage(filepath);
        try {
            tasklist = storage.loadFromFile();
        } catch (ChatException e) {
            System.err.println("File failed to load");
            tasklist = new TaskList();
        }
    }

    /**
     * Interacts with chat, sends command and receives output.
     * @param input entered by user.
     * @return output from chat.
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

    public boolean hasExited() {
        return isExit;
    }
}
