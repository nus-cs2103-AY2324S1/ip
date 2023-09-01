package duke;

/**
 * This class helps Duke interact with the user.
 */
public class Ui {
    protected static final String horizontalLine = "    ____________________________________________________________";
    public static void greet() {
        System.out.println(horizontalLine + "\n"
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine + "\n"
                + "     Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }
}
