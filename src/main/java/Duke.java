import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> tasks = new ArrayList<>();

    private enum Colors {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        ;

        private final String code;

        private Colors(String c) {
            code = c;
        }

        @Override
        public String toString() {
            return code;
        }
    }

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
                        for (int i = 0; i < tasks.size(); i++) {
                            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
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
                        if (choice < 0 || choice >= tasks.size()) {
                            throw new DukeException("Argument Provided out of range: " + (choice+1));
                        }
                        tasks.get(choice).markAsDone();
                        sendMessage("Nice! I've marked this task as done:\n  " + tasks.get(choice));
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
                        if (choice < 0 || choice >= tasks.size()) {
                            throw new DukeException("Argument Provided out of range: " + (choice+1));
                        }
                        tasks.get(choice).markAsNotDone();
                        sendMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(choice));
                        break;

                    case "delete":
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: " + command);
                        }
                        try {
                            choice = Integer.parseInt(arguments) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("Invalid Argument Provided: " + arguments);
                        }
                        if (choice < 0 || choice >= tasks.size()) {
                            throw new DukeException("Argument Provided out of range: " + (choice+1));
                        }
                        sendMessage("Noted. I've removed this task:\n  " +
                                tasks.get(choice) +
                                String.format("\nNow you have %d tasks in the list.", tasks.size() - 1));
                        tasks.remove(choice);
                        break;

                    case "todo":
                        if (Objects.equals(arguments, "")) {
                            throw new DukeException("Missing Argument for command: " + command);
                        }
                        tasks.add(new Todo(arguments));
                        sendMessage("Got it. I've added this task:\n  " +
                                tasks.get(tasks.size()-1) +
                                String.format("\nNow you have %d tasks in the list.", tasks.size()));
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
                        tasks.add(new Deadline(userArgs[0], userArgs[1]));
                        sendMessage("Got it. I've added this task:\n  " +
                                tasks.get(tasks.size()-1) +
                                String.format("\nNow you have %d tasks in the list.", tasks.size()));
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
                        tasks.add(new Event(userArgs[0], userArgs[1], userArgs[2]));
                        sendMessage("Got it. I've added this task:\n  " +
                                tasks.get(tasks.size()-1) +
                                String.format("\nNow you have %d tasks in the list.", tasks.size()));
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
            System.out.println("\t" + Colors.CYAN + msg + Colors.RESET);
        }
        printLine();
    }

    public static void sendError(String msgs) {
        printLine();
        for (String msg: msgs.split("\n")) {
            System.out.println("\t" + Colors.RED + msg + Colors.RESET);
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
