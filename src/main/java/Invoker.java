import java.util.function.Consumer;
import java.util.Dictionary;
import java.util.Hashtable;

public class Invoker {
    /**
     * Lookup Table that relates a keyword to a corresponding method.
     */
    private static Dictionary<String, Consumer<Parser>> COMMAND_LIST = new Hashtable<String, Consumer<Parser>>() {{
        put("bye", new CommandBye());
        put("list", new CommandTaskList());
        put("mark", new CommandTaskMark(true));
        put("unmark", new CommandTaskMark(false));
        put("todo", new CommandTaskCreate(TaskTypes.TODO));
        put("deadline" ,new CommandTaskCreate(TaskTypes.DEADLINE));
        put("event", new CommandTaskCreate(TaskTypes.EVENT));
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
    public static void handle(String inputString) {
        Parser input = new Parser(removeFirstWord(inputString));
        String keyword = inputString.split(" ")[0];
        Consumer<Parser> calledConsumer = COMMAND_LIST.get(keyword);
        if (calledConsumer != null) {
            try {
                calledConsumer.accept(input);
            } catch (IllegalArgumentException e) {
                Rock.respond(e.getMessage());
            }
        } else {
            unknownCommand(inputString);
        }
    }
    /**
     * Handler for unknown commands given by user.
     * @param input User's input
     */
    private static void unknownCommand(String input) {
        Rock.respond("Unknown command!");
    }
}
