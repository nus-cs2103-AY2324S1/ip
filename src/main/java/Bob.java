import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
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
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int pointer = 0;
    private static boolean isActive = true;
    private static final Scanner scanner = new Scanner(System.in);
    public enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public static void main(String[] args) {
        Bob.printWelcomeMessage();
        try {
            processResponse();
        } catch (BobException e) {
            System.out.println(e.getMessage() + divider);
        }
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

    private static Commands parseCommand(String keyword) {
        try {
            return Commands.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            return Commands.INVALID;
        }
    }

    private static void processResponse() throws BobException {
        String displayMessage = "I gotchu. New task added to the list:\n";
        String input = Bob.scanner.nextLine();
        String[] inputSplit = input.split(" ", 2);
        String command = inputSplit[0].toUpperCase();
        String argument = "";

        if (inputSplit.length > 1) {
            argument = inputSplit[1];
        }
        try {
            switch (parseCommand(command)) {
                case BYE:
                    Bob.printGoodbyeMessage();
                    Bob.isActive = false;
                    break;
                case LIST:
                    if (pointer == 0) {
                        System.out.println("You don't have any tasks. Good job!" + divider);
                        break;
                    }
                    for (int i = 0; i < pointer; i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println(divider);
                    break;
                case MARK:
                    if (argument.isBlank()) {
                        System.out.println("Tell me which task to mark as done! Give me an integer number!" + divider);
                        break;
                    }
                    try {
                        int markIndex = Integer.parseInt(argument);
                        tasks.get(markIndex - 1).markAsDone();
                        System.out.println("Great Job! I've helped mark this task as done:\n" +
                                tasks.get(markIndex - 1).toString() + divider);
                    } catch (NumberFormatException e) {
                        System.out.println("The mark command must be followed by an integer number." + divider);
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("You are trying to mark a non-existent task, ensure you mark a task that you have created :O" + divider);
                    }
                    break;
                case UNMARK:
                    if (argument.isBlank()) {
                        System.out.println("Tell me which task to mark as done! Give me an integer number!" + divider);
                        break;
                    }
                    try {
                        int unmarkIndex = Integer.parseInt(argument);
                        tasks.get(unmarkIndex - 1).unmarkTask();
                        System.out.println("No worries! I will help you unmark this task:\n" +
                                tasks.get(unmarkIndex - 1).toString() + divider);
                    } catch (NumberFormatException e) {
                        System.out.println("The mark command must be followed by an integer number." + divider);
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("You are trying to unmark a non-existent task, ensure you mark a task that you have created :O" + divider);
                    }
                    break;
                case TODO:
                    if (argument.isBlank()) {
                        System.out.println("The description of your todo should not be empty! Try:\ntodo [description]" + divider);
                        break;
                    }
                    Bob.tasks.add(new Todo(argument));
                    System.out.println(displayMessage + Bob.tasks.get(pointer).toString());
                    Bob.pointer++;
                    printListMessage();
                    break;
                case DEADLINE:
                    if (argument.isBlank()) {
                        System.out.println("The description of your deadline should not be empty! Try:\ndeadline [description] /by [duedate]" + divider);
                        break;
                    }
                    String[] bySplit = argument.split(" /by ", 2);
                    if (bySplit.length != 2) {
                        System.out.println("Incorrect deadline command format! It should be:\ndeadline [description] /by [duedate]" + divider);
                        break;
                    }
                    Bob.tasks.add(new Deadline(bySplit[0], bySplit[1]));
                    System.out.println(displayMessage + Bob.tasks.get(pointer).toString());
                    Bob.pointer++;
                    printListMessage();
                    break;
                case EVENT:
                    if (argument.isBlank()) {
                        System.out.println("The description of your event should not be empty! Try:\nevent [description] /from [start] /to [end]" + divider);
                        break;
                    }
                    String[] descSplit = argument.split(" /from ", 2);
                    if (descSplit.length != 2) {
                        System.out.println("Incorrect event command format! It should be:\nevent [description] /from [start] /to [end]" + divider);
                        break;
                    }
                    String[] periodSplit = descSplit[1].split(" /to ", 2);
                    if (periodSplit.length != 2) {
                        System.out.println("Incorrect event command format! It should be:\nevent [description] /from [start] /to [end]" + divider);
                        break;
                    }
                    Bob.tasks.add(new Event(descSplit[0], periodSplit[0], periodSplit[1]));
                    System.out.println(displayMessage + Bob.tasks.get(pointer).toString());
                    Bob.pointer++;
                    printListMessage();
                    break;
                case DELETE:
                    if (argument.isBlank()) {
                        System.out.println("Tell me which task to delete! Give me an integer number!" + divider);
                        break;
                    }
                    try {
                        int delIndex = Integer.parseInt(argument);
                        Task deletedTask = tasks.remove(delIndex - 1);
                        System.out.println("Foosh! Let it be gone! I've helped delete the task:\n" +
                                deletedTask.toString());
                        Bob.pointer--;
                        printListMessage();
                    } catch (NumberFormatException e) {
                        System.out.println("The delete command must be followed by an integer number." + divider);
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("You are trying to delete a non-existent task, ensure you delete a task that you have created :3" + divider);
                    }
                    break;
                case INVALID:
                    System.out.println("I'm sorry! I don't understand the command :( " + divider);
                    break;
            }

            if (Bob.isActive) {
                processResponse();
            }

        } catch (Exception e) {
            throw new BobException("An unknown error has occurred. I'll shut myself off for now.");
        }
    }


}
