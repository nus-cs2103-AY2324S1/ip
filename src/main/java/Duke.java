import java.util.Scanner;

public class Duke {
    private static final String divider = "\n____________________________________________________________\n";
    private static final String logo =
            ".-. .-')              .-. .-')   \n" +
            "\\  ( OO )             \\  ( OO )  \n" +
            " ;-----.\\  .-'),-----. ;-----.\\  \n" +
            " | .-.  | ( OO'  .-.  '| .-.  |  \n" +
            " | '-' /_)/   |  | |  || '-' /_) \n" +
            " | .-. `. \\_) |  |\\|  || .-. `.  \n" +
            " | |  \\  |  \\ |  | |  || |  \\  | \n" +
            " | '--'  /   `'  '-'  '| '--'  / \n" +
            " `------'      `-----' `------'  ";

    private static Boolean isActive = true;
    private static final String[] tasks = new String[100];
    private static int pointer = 0;
    public static void main(String[] args) {
        Duke.printWelcomeMessage();
        echo();
    }
    private static void printWelcomeMessage() {
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        System.out.println(divider + logo + "\n" + welcomeMessage + divider);
    }

    private static void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Bob signing out!";
        System.out.println(goodbyeMessage + divider);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String displayMessage = "added: ";

        while (isActive) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                Duke.printGoodbyeMessage();
                isActive = false;
            } else if (command.equals("list")) {
                for (int i = 0; i < pointer; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(divider);
            } else {
                Duke.tasks[pointer] = command;
                Duke.pointer++;
                System.out.println(displayMessage + command + divider);
            }
        }
    }
}
