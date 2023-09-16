package messages;

/**
 * This class encapsulates all the Messages the application will use.
 * It is packaged into its own class for reusability and readability.
 * It is recommended to statically import the specific MESSAGE_{type} that needs to be used.
 */
public class Message {

    public static final String LOGO = "  OOOO                         OOOO\n"
                                    + " O    O     w           w     O    O\n"
                                    + " O    O      w   w w   w      O    O\n"
                                    + " O    O       w w   w w       O    O\n"
                                    + "  OOOO         w     w         OOOO";

    public static final String MESSAGE_WELCOME =
            "Hello >u<! I'm OwO_bot\n\n"
            + LOGO + "\n\n"
            + "How can I help today ♥w♥ ?";

    public static final String MESSAGE_EXIT = "Bye! Hope to see you again soon! x3";

    public static final String MESSAGE_INSTRUCTIONS =
            "List of available commands:\n" + "list\n"
          + "todo <task name>\n"
          + "deadline <task name> /by <yyyy-MM-dd HH-mm>\n"
          + "event <task name> /from <yyyy-MM-dd HH-mm> /to <yyyy-MM-dd HH-mm>\n"
          + "mark <task number>\n" + "unmark <task number>\n"
          + "find <task description>\n"
          + "delete <task number>\n" + "exit";

    public static final String MESSAGE_VERSION = "NotCrazy ─ a CS2103T iP ─ Week 5 Update";
}
