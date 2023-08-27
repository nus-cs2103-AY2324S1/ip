package ui;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
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
                        "Datetime format for deadline/events: dd/MM/yyyy hhmm (e.g. 13/01/2020 1800)\n" +
                        "Type 'help' to access the help menu!\n" +
                        "What can I do(t) for you?\n%s\n",
                HORIZONTAL_RULE, LOGO, HORIZONTAL_RULE);
    }

    /**
     * Displays help message with descriptions of all commands
     */
    public static void displayHelpMessage() {
        String helpMessage = "Welcome to the help menu, DonT worry ^o^!\n" +
                "<datetime> format for deadline/events: dd/MM/yyyy hhmm (e.g. 13/01/2020 1800)\n" +
                "<date> format for whatsgoingon: dd/MM/yyyy (e.g. 13/01/2020)\n\n" +
                "Commands:\n" +
                "todo <description> – Add a todo task\n" +
                "deadline <description> /by <deadline_description/datetime>\n" +
                "event <description> /from <start_description/datetime> /to <start_description/datetime>\n" +
                "list – List out all your tasks\n" +
                "unmark <taskNo> – unmark task based on no. on the list\n" +
                "mark <taskNo> – check off task based on no. on the list\n" +
                "delete <taskNo> – delete task based on no. on the list\n" +
                "whatsgoingon <date> – see what deadlines/events are ongoing for given date\n" +
                "help – access this help menu\n" +
                "bye – close Dot";
        Ui.wrapPrintWithHorizontalRules(helpMessage);
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

    public String readNextLine() {
        return this.sc.nextLine();
    }
}
