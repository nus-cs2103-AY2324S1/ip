import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greet();

        boolean active = true;
        while (active) {
            String[] userInput = readMessage().split(" ", 2);
            String command = userInput[0];
            String arguments = userInput.length == 2 ? userInput[1] : "";
            switch (command){
                case "bye":
                    active = false;
                    break;

                case "list":
                    StringBuilder output = new StringBuilder();
                    for (int i = 0; i < taskCount; i++) {
                        output.append(i + 1).append(". ").append(tasks[i]).append("\n");
                    }
                    if (output.length() == 0) {
                        sendMessage("No Items in List");
                    } else {
                        sendMessage(output.toString());
                    }
                    break;

                case "mark":
                    int choice = Integer.parseInt(arguments) - 1;
                    if (choice < 0 || choice >= taskCount) {
                        sendMessage("Invalid Choice Provided!");
                        continue;
                    }
                    tasks[choice].markAsDone();
                    sendMessage("Nice! I've marked this task as done:\n  " + tasks[choice]);
                    break;

                case "unmark":
                    choice = Integer.parseInt(arguments) - 1;
                    if (choice < 0 || choice >= taskCount) {
                        sendMessage("Invalid Choice Provided!");
                        continue;
                    }
                    tasks[choice].markAsNotDone();
                    sendMessage("OK, I've marked this task as not done yet:\n  " + tasks[choice]);
                    break;

                case "todo":
                    tasks[taskCount] = new Todo(arguments);
                    taskCount++;
                    sendMessage("Got it. I've added this task:\n  " +
                            tasks[taskCount-1] +
                            String.format("\nNow you have %d tasks in the list.", taskCount));
                    break;

                case "deadline":
                    String[] userArgs = arguments.split("/by ");
                    tasks[taskCount] = new Deadline(userArgs[0], userArgs[1]);
                    taskCount++;
                    sendMessage("Got it. I've added this task:\n  " +
                            tasks[taskCount-1] +
                            String.format("\nNow you have %d tasks in the list.", taskCount));
                    break;

                case "event":
                    userArgs = arguments.split("/from |/to ");
                    tasks[taskCount] = new Event(userArgs[0], userArgs[1], userArgs[2]);
                    taskCount++;
                    sendMessage("Got it. I've added this task:\n  " +
                            tasks[taskCount-1] +
                            String.format("\nNow you have %d tasks in the list.", taskCount));
                    break;
                default:
                    sendMessage("Invalid Command, please try again");
                    break;
            }
        }
        exit();
    }

    private static void greet() {
        sendMessage("Hello! I'm Heimdallr\nWhat can I do for you?");
    }

    private static void exit() {
        sendMessage(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String msgs) {
        printLine();
        for (String msg: msgs.split("\n")) {
            System.out.println("\t" + msg);
        }
        printLine();
    }

    public static String readMessage() {
        return scanner.nextLine();
    }

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}
