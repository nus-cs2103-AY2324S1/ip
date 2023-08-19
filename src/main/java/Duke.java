import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greet();

        boolean active = true;
        while (active) {
            try {
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
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: mark");
                        }
                        int choice = -1;
                        try {
                            choice = Integer.parseInt(arguments) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("Invalid Argument Provided: " + arguments);
                        }
                        if (choice < 0 || choice >= taskCount) {
                            throw new DukeException("Argument Provided out of range: " + choice);
                        }
                        tasks[choice].markAsDone();
                        sendMessage("Nice! I've marked this task as done:\n  " + tasks[choice]);
                        break;

                    case "unmark":
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: " + command);
                        }
                        try {
                            choice = Integer.parseInt(arguments) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("Invalid Argument Provided: " + arguments);
                        }
                        if (choice < 0 || choice >= taskCount) {
                            throw new DukeException("Argument Provided out of range: " + choice);
                        }
                        tasks[choice].markAsNotDone();
                        sendMessage("OK, I've marked this task as not done yet:\n  " + tasks[choice]);
                        break;

                    case "todo":
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: " + command);
                        }
                        tasks[taskCount] = new Todo(arguments);
                        taskCount++;
                        sendMessage("Got it. I've added this task:\n  " +
                                tasks[taskCount-1] +
                                String.format("\nNow you have %d tasks in the list.", taskCount));
                        break;

                    case "deadline":
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: " + command);
                        }
                        String[] userArgs = arguments.split("/by ");
                        if (userArgs.length != 2) {
                            throw new DukeException("Missing Argument for command: " + command + ", should include /by [date]");
                        }
                        if (Objects.equals(userArgs[1], "")) {
                            throw new DukeException("Missing Argument for command: " + command + ", should include /by [date]");
                        }
                        tasks[taskCount] = new Deadline(userArgs[0], userArgs[1]);
                        taskCount++;
                        sendMessage("Got it. I've added this task:\n  " +
                                tasks[taskCount-1] +
                                String.format("\nNow you have %d tasks in the list.", taskCount));
                        break;

                    case "event":
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: " + command);
                        }
                        userArgs = arguments.split("/from |/to ");
                        if (userArgs.length != 3) {
                            throw new DukeException("Missing Argument for command: " +
                                    command +
                                    ", should include /from [date] /to [date]");
                        }
                        if (Objects.equals(userArgs[1], "") || Objects.equals(userArgs[2], "")) {
                            throw new DukeException("Missing Argument for command: " +
                                    command +
                                    ", should include /from [date] /to [date]");
                        }
                        tasks[taskCount] = new Event(userArgs[0], userArgs[1], userArgs[2]);
                        taskCount++;
                        sendMessage("Got it. I've added this task:\n  " +
                                tasks[taskCount-1] +
                                String.format("\nNow you have %d tasks in the list.", taskCount));
                        break;
                    default:
                        throw new DukeException("Invalid Command: " + command + " , Please Try Again...");
                }
            } catch (DukeException e){
                sendError(e.toString());
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

    public static void sendError(String msgs) {
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
