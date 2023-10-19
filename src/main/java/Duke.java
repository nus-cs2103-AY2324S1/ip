import dukepackage.ChatGUI;
import dukepackage.Storage;

/**
 * The Duke class is the main class that runs the Duke chatbot application.
 */
public class Duke{

    protected static Storage storage = new Storage();
    protected static ChatGUI ui = new ChatGUI(storage);
    protected String getResponse(String input) {
        return ui.process(input);
    }

}