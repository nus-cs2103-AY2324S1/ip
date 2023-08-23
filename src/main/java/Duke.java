package main.java;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "    ____________________________________________________________\n";
    public static void greet() {
        System.out.println(horizontalLine
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + horizontalLine);
    }
    public static void exit() {
        System.out.println("     Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }
    public static void printCommand(String command) {
        if (Objects.equals(command, "bye")) {
            exit();
        } else {
            System.out.println(horizontalLine
                    + "     " + command + "\n"
                    + horizontalLine);
        }
    }
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            printCommand(command);
        }
    }
}
