package duke.ui;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public static void printLines(String ...lines) {
        System.out.println("\t" + LINE);
        for (String line : lines) {
            System.out.println("\t " + line);
        }
        System.out.println("\t" + LINE);
    }

    public static void greetUser() {
        printLines("Hello I'm ADJ", "What can I do for you?");
    }

    public static void printExitMessage() {
        printLines("Bye. Hope to see you again soon!");
    }
}
