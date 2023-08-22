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
        processResponse();
    }

    private static void printWelcomeMessage() {
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        System.out.println(divider + logo + "\n" + welcomeMessage + divider);
    }

    private static void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Bob signing out!";
        System.out.println(goodbyeMessage + divider);
    }

    private static void printListMessage() {
        if (pointer == 1) {
            System.out.println(String.format("\nNow you have %d task in your list!", pointer) + divider);
        } else {
            System.out.println(String.format("\nNow you have %d tasks in your list!", pointer) + divider);
        }
    }

    private static void processResponse() {
        Scanner scanner = new Scanner(System.in);
        String displayMessage = "I gotchu. New task added to the list:\n";


        while (isActive) {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);
            String command = inputSplit[0].toUpperCase();
            String argument = "";

            if (inputSplit.length > 1) {
                argument = inputSplit[1];
            }

            switch (command) {
                case "BYE":
                    isActive = false;
                    Duke.printGoodbyeMessage();
                    break;
                case "LIST":
                    for (int i = 0; i < pointer; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toString());
                    }
                    System.out.println(divider);
                    break;
                case "MARK":
                    int markIndex = Integer.parseInt(argument);
                    tasks[markIndex - 1].markAsDone();
                    System.out.println("Great Job! I've helped mark this task as done:\n" +
                            tasks[markIndex - 1].toString() + divider);
                    break;
                case "UNMARK":
                    int unmarkIndex = Integer.parseInt(argument);
                    tasks[unmarkIndex - 1].unmarkTask();
                    System.out.println("No worries! I will help you unmark this task:\n" +
                            tasks[unmarkIndex - 1].toString() + divider);
                    break;
                case "TODO":
                    Duke.tasks[pointer] = new Todo(argument);
                    System.out.println(displayMessage + Duke.tasks[pointer].toString());
                    Duke.pointer++;
                    printListMessage();
                    break;
                case "DEADLINE":
                    String[] bySplit = argument.split(" /by ", 2);
                    Duke.tasks[pointer] = new Deadline(bySplit[0], bySplit[1]);
                    System.out.println(displayMessage + Duke.tasks[pointer].toString());
                    Duke.pointer++;
                    printListMessage();
                    break;
                case "EVENT":
                    String[] descSplit = argument.split(" /from ", 2);
                    String[] periodSplit = descSplit[1].split(" /to ",2);
                    Duke.tasks[pointer] = new Event(descSplit[0], periodSplit[0], periodSplit[1]);
                    System.out.println(displayMessage + Duke.tasks[pointer].toString());
                    Duke.pointer++;
                    printListMessage();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
