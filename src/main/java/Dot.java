public class Dot {
    // TODO: Create new folders utility & views, for constants etc., and UI
    // Constants - Display, UI
    public static String HORIZONTAL_RULE = "_".repeat(60) + "\n";

    @SuppressWarnings("unused")
    public static String logo = " ____          _\n"
            + "|  _ \\ _____ _| |___\n"
            + "| | | | | | | | ____|\n"
            + "| |_| | |_| | | |___\n"
            + "|____/ \\___/  \\____/\n";

    // Constants - Utility

    // Class methods - Display, UI
    public static void welcome() {
        System.out.printf("%sHello! I'm Dot, " +
                "let me help you finish your tasks on the dot!\n" +
                "What can I do for you?\n", HORIZONTAL_RULE);
    }

    public static void goodbye() {
        System.out.printf("%sBye. Hope to see you again soon!\n%s",
                HORIZONTAL_RULE, HORIZONTAL_RULE);
    }

    public static void main(String[] args) {
        welcome();
        goodbye();
    }
}
