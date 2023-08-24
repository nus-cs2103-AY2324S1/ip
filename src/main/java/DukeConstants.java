/**
 * Constants of the application.
 */
public class DukeConstants {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    public static final String GREETING_MESSAGE = "\t" +
            HORIZONTAL_LINE +
            "\n\t Hello! I'm ChatNat!\n" +
            "\t What can I do for you?\n\t" +
            HORIZONTAL_LINE;

    public static final String LIST_MESSAGE = "\t" +
            " Here are the tasks in your list:";
    public static final String MARKED_MESSAGE = "\t" +
            " Nice! I've marked this task as done:\n" +
            "\t   %s\n\t" +
            HORIZONTAL_LINE;

    public static final String INSERT_MESSAGE = "\t Got it. I've added this task:\n" +
            "\t   %s\n\t Now you have %d %s in the list.\n\t" + HORIZONTAL_LINE;
    public static final String UNMARKED_MESSAGE = "\t" +
            " OK, I've marked this task as not done yet:\n" +
            "\t   %s\n\t" +
            HORIZONTAL_LINE;
    public static final String EXIT_MESSAGE =
            "\t Bye. Hope to see you again soon!\n\t" + HORIZONTAL_LINE;
}
