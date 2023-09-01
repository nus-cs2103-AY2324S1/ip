package ui;

public class Ui {
    private static final String LINE = "---------------------------------------------------------";

    public static void sayHello() {
        String firstMessage = LINE + "\nHello! I'm Hong\nWhat can I do for you?\n" + LINE;
        System.out.println(firstMessage);
    }

    public static void sayBye() {
        String exitMessage = LINE + "\n" + "Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(exitMessage);
    }

    public static void print(String printString) {
        System.out.println(printString);
    }
}
