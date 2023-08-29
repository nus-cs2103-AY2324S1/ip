package duke.ui;

public class Ui {

    public static final String DIVIDER = "____________________________________________________________";

    public static void println(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    public static void printlns(String[] input) {
        System.out.println(DIVIDER);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
        System.out.println(DIVIDER);
    }

    public static void greet() {
        Ui.printlns(new String[] { "Hello! I'm LilBro!", "What can I do for you?" });
    }

    public static void bye() {
        Ui.println("Bye. Hope to see you again soon!");
    }
}
