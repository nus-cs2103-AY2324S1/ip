import java.util.function.Consumer;
import java.util.Dictionary;
import java.util.Hashtable;

public class CommandHandler {
    /**
     * Lookup Table that relates a keyword to a corresponding method.
     */
    private static Dictionary<String, Consumer<String>> COMMAND_LIST = new Hashtable<String, Consumer<String>>() {{
        put("bye", CommandHandler::bye);
    }};
    /**
     * Helper function used to obtain the rest of a sentence sans keyword.
     * @param sentence String to be trimmed.
     * @return Same string without keyword, empty string if there is no other characters
     * besides keyword
     */
    private static String removeFirstWord(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words.length > 1 ? words[1] : "";
    }
    /**
     * Used to handle a given user input and call the corresponding method.
     * @param inputString User's input.
     */
    public static void invoke(String inputString) {
        String inputWords = removeFirstWord(inputString);
        String keyword = inputString.split(" ")[0];
        Consumer<String> calledConsumer = COMMAND_LIST.get(keyword);
        if (calledConsumer != null) {
            calledConsumer.accept(inputWords);
        } else {
            unknownCommand(inputString);
        }
    }
    /**
     * Terminates chatbot
     * @param inputString Unused
     */
    private static void bye(String inputString) {
        Rock.terminate();
    }
    /**
     * Handler for unknown commands given by user.
     * @param inputString User's input
     */
    private static void unknownCommand(String inputString) {
        echo(inputString);
    }
    /**
     * Prints out the user's input.
     * @param inputString User's input.
     */
    private static void echo(String inputString) {
        Rock.say(inputString);
        Rock.say(Rock.LINE_BREAK);
    }
}
