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
    private static final Task[] tasks = new Task[100];
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
            String input = scanner.nextLine();
            String[] stringSplit = input.split(" ");
            String command = stringSplit[0];

            switch (command) {
                case "bye":
                    Duke.printGoodbyeMessage();
                    isActive = false;
                    break;
                case "list":
                    for (int i = 0; i < pointer; i++) {
                        System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                    }
                    System.out.println(divider);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(stringSplit[1]);
                    tasks[markIndex - 1].markAsDone();
                    System.out.println("Great Job! I've helped mark this task as done:\n" +
                            "[" + tasks[markIndex - 1].getStatusIcon() + "] " + tasks[markIndex - 1].description + divider);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(stringSplit[1]);
                    tasks[unmarkIndex - 1].unmarkTask();
                    System.out.println("No worries! I will help you unmark this task:\n" +
                            "[" + tasks[unmarkIndex - 1].getStatusIcon() + "] " + tasks[unmarkIndex - 1].description + divider);
                    break;
                default:
                    Duke.tasks[pointer] = new Task(input);
                    Duke.pointer++;
                    System.out.println(displayMessage + input + divider);
                    break;
            }
        }
    }
}
