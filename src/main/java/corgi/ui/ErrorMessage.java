package corgi.ui;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines error messages for various exceptions in the application.
 * It provides a mapping between exception class names and their corresponding error messages.
 */
public class ErrorMessage {
    /**
     * A mapping between exception class names and their corresponding error messages.
     */
    public static final Map<String, String> mapper = new HashMap<>();

    static {
        mapper.put("InvalidCommandTypeException",
                "Can't believe you're asking that! Grrr, what do you want now?");
        mapper.put("InvalidCommandFormatException",
                "Are you trying to confuse me with this nonsense? Try again hooman!");
        mapper.put("CommandExecutionException",
                "Argh, something went wrong! Bad hooman, what are you trying to do!?");
    }
}
