import java.util.Scanner;

public class Duke {

    private static void printOutput(String output) {
        String horizontalLine = "    ____________________________________________________________";
        System.out.printf(horizontalLine + "\n    " + output + '\n' + horizontalLine + '\n');
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                printOutput("Bye. Hope to see you again soon!");
                break;
            } else {
                printOutput(command);
            }
        }

    }

    public static void main(String[] args) {

        printOutput("Hello! I'm Nano\n    What can I do for you?");
        handleCommand();

    }
}
