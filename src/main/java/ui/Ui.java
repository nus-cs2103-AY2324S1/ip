package ui;

public class Ui {
    public static final String HORIZONTAL_RULE = "_".repeat(80) + "\n";

    private static final String LOGO = " ____          _\n"
            + "|  _ \\ _____ _| |___\n"
            + "| | | | | | | | ____|\n"
            + "| |_| | |_| | | |___\n"
            + "|____/ \\___/  \\____/\n";

    /**
     * Displays the welcome message for the user.
     */
    public static void welcome() {
        System.out.printf("%s%sHello! I'm Dot, " +
                        "let me help you finish your tasks on the dot!\n" +
                        "What can I do(t) for you?\n%s\n",
                HORIZONTAL_RULE, LOGO, HORIZONTAL_RULE);
    }

    /**
     * Displays the exit message for the user.
     */
    public static void goodbye() {
        System.out.printf("%sBye! DOnT forget to finish your tasks!\n%s",
                HORIZONTAL_RULE, HORIZONTAL_RULE);
    }

    /**
     * Displays argument with horizontal rules.
     */
    public static void wrapPrintWithHorizontalRules(String msg) {
        System.out.printf("%s%s\n%s\n",
                HORIZONTAL_RULE, msg, HORIZONTAL_RULE);
    }

    public static void displayMarkOrUnmark(boolean isMarking, String task) {
        if (isMarking) {
            wrapPrintWithHorizontalRules(String.format(
                    "Nice! I've marked this task as done:\n  %s", task));
        } else {
            wrapPrintWithHorizontalRules(String.format(
                    "OK, I've marked this task as not done yet:\n  %s", task));
        }

    }
}
