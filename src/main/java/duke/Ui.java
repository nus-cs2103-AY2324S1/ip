package duke;

public class Ui {

    public static void showIdentity() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Snow!");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Failed to load.");
    }
}
