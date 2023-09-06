package duke;

/**
 * This class helps Duke interact with the user.
 */
public class Ui {
    protected static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    public static void greet() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + HORIZONTAL_LINE);
    }
    public static void exit() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "     Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }
}
