package duke;

/**
 * A class that contains all predefined messages.
 */
public class Messages {
    private static final String CHAT_BOT_NAME = "Genos";
    static final String GREETING = "Hello I'm " + CHAT_BOT_NAME + ".\n"
        + "Please type your command below, I will store what you said" + "\n"
        + "Usage: \"list\" to see the list of text stored, \"bye\" to exit" + "\n"
        + "\"mark [number]\" to mark task no. [number] to be isDone, "
        + "\"unmark [number]\" to mark it as undone" + "\n"
        + "\"todo [description]\" to add todo, \"event [description] /from [date] /to [date]\" "
        + "to add event," + "\n"
        + "\"deadline [description] /by [date]\" to add deadline";

}
