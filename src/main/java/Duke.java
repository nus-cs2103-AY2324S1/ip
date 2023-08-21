import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "      ____________________________________________________________\n";
    public static void greet() {
        System.out.println(horizontalLine
                            + "      " + "Hello! I'm Glenda!\n"
                            + "      " + "What can I do for you?\n"
                            + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine
                            + "      " + "Bye. Hope to see you again soon!\n"
                            + horizontalLine);
    }

    public static void printCommand(String command) {
        System.out.println(horizontalLine
                            + "      " + command + "\n"
                            + horizontalLine);
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            printCommand(command);
            command = scanner.nextLine();
        }

        exit();
    }
}
