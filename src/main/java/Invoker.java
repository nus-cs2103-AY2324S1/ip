import java.util.function.Consumer;
/**
 * Used to handle input given by chatbot
 * and call the appropriate commands with
 * the parsed data
 * 
 * @author Alvis Ng (supermii2)
 */
public class Invoker {
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
    public static void handle(String inputString) {
        Parser input = new Parser(removeFirstWord(inputString));
        String keyword = inputString.split(" ")[0];
        try {
            Consumer<Parser> calledConsumer = Command.getCommand(keyword);
            calledConsumer.accept(input);
        } catch (IllegalArgumentException e) {
            Rock.respond(e.getMessage());
        }
    }
}
