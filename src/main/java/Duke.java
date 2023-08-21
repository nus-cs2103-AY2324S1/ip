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
        System.out.println(goodbyeMessage + "\n" + divider);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);

        while (isActive) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                Duke.printGoodbyeMessage();
                isActive = false;
            } else {
                System.out.println(command + "!" + divider);
            }
        }
    }
}
