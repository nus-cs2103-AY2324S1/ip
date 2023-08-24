import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        UNKNOWN
    }
    static String horizontal_line = "____________________________________________________________\n";

    /**
     * The main  method for the Duke application.
     * It initializes the task list and listens for user commands,
     * handling them appropriately based on their type.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        handleStart();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String userCommand = scanner.nextLine();
            CommandType commandType = getCommandType(userCommand);
            switch(commandType) {
                case LIST:
                    handleList(tasks);
                    break;
                case BYE:
                    handleBye(scanner);
                    isRunning = false;
                    break;
                case DELETE:
                    handleDelete(userCommand,tasks);
                    break;
                case MARK:
                    handleMark(userCommand, tasks);
                    break;
                case UNMARK:
                    handleUnmark(userCommand, tasks);
                    break;
                case EVENT:
                    handleEvent(userCommand, tasks);
                    break;
                case DEADLINE:
                    handleDeadline(userCommand, tasks);
                    break;
                case TODO:
                    handleTodo(userCommand, tasks);
                    break;
                case UNKNOWN:
                    handleUnknown();
                    break;
            }
        }
    }

    /**
     * Determines type of command entered by the user.
     *
     * @param userCommand The command string entered by the user.
     * @return The type of command.
     */
    public static CommandType getCommandType(String userCommand) {
        if ("bye".equals(userCommand)) {
            return CommandType.BYE;
        } else if ("list".equals(userCommand)) {
            return CommandType.LIST;
        } else if (userCommand.startsWith("delete ")) {
            return CommandType.DELETE;
        } else if (userCommand.startsWith("mark ")) {
           return CommandType.MARK;
        } else if (userCommand.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (userCommand.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (userCommand.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (userCommand.startsWith("event ")) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Generates a message showing number of tasks in the list.
     *
     * @param numTasks The number of tasks currently in the list.
     * @return The formatted message.
     */
    public static String updateNumMessage(String numTasks) {
        return "Now you have " + numTasks + " task(s) in the list";
    }

    /**
     * Handles cases when the user provides an unrecognized command.
     * Outputs an error message to inform the user that the command is unknown.
     */
    public static void handleUnknown() {
        System.out.println("unknown command");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks A list of tasks to be displayed.
     * Each task is enumerated starting from 1 and printed using its string representation.
     */
    public static void handleList(List<Task> tasks) {
        System.out.println(horizontal_line);
        System.out.println("here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        System.out.println(horizontal_line);
    }

    /**
     * Handles the deletion of a task from the list based on the user command.
     *
     * @param userCommand The command given by the user to delete a task.
     * @param tasks A list of tasks from which a task will be deleted.
     */
    public static void handleDelete(String userCommand, List<Task> tasks) {
        String[] parts = userCommand.split(" ", 2);
        Task removedTask;
        try {
            if (parts.length != 2) {
                throw new DukeException("invalid delete command");
            }
            int num = Integer.parseInt(parts[1]);
            if (num > tasks.size()) {
                throw new DukeException("invalid delete command (this task number does not exist)");
            }
            removedTask = tasks.remove(num - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println(updateNumMessage(String.valueOf(tasks.size())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the marking of a task as completed based on the user command.
     *
     * @param userCommand The command given by the user to mark a task as completed.
     * @param tasks A list of tasks from which a specific task will be marked as completed.
     */
    public static void handleMark(String userCommand, List<Task> tasks) {
        String[] parts = userCommand.split(" ");
        try {
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.get(num - 1);
                task.toggleCompleted();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else {
                throw new DukeException("invalid mark command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the unmarking of a task, reverting its status to not completed based on the user command.
     *
     * @param userCommand The command given by the user to unmark a task.
     * @param tasks A list of tasks from which a specific task will be unmarked.
     */
    public static void handleUnmark(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ");
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.get(num - 1);
                task.toggleCompleted();
                System.out.println("Okay. I see you haven't done this task yet");
                System.out.println(task);
            } else {
                throw new DukeException("invalid unmark command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initialises and displays the welcome message for the Duke application.
     */
    public static void handleStart() {
        String welcomeMessage = horizontal_line +
                " Hello! I'm Blob\n" +
                " What can I do for you?\n" +
                horizontal_line;
        System.out.println(welcomeMessage);
    }

    /**
     * Adds a "todo" task to the task list and provides user feedback.
     *
     * @param userCommand The user input command string.
     * @param tasks The current list of tasks.
     */
    public static void handleTodo(String userCommand, List<Task> tasks) {
        try {
            String description ="";
            String[] parts = userCommand.split(" ", 2);
            if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                description = parts[1];
            }
            Task currTask = new ToDos(description);
            tasks.add(currTask);
            String numTasks = String.valueOf(tasks.size());
            System.out.println("Got it I have added this task:" + "\n" +  currTask);
            System.out.println(updateNumMessage(numTasks));
            System.out.println(horizontal_line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a "deadline" task to the task list and provides user feedback.
     *
     * @param userCommand The user input command string.
     * @param tasks The current list of tasks.
     */
    public static void handleDeadline(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ", 2);
            String description = "";
            String date = "";
            String secondPart = "";
            if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                secondPart = parts[1];
            }
            String[] finalParts = secondPart.split(" /by ", 2);
            if (finalParts.length >= 2) {
                description = finalParts[0];
                date = finalParts[1];
            }
            Task currTask = new Deadlines(description, date);
            tasks.add(currTask);
            String numTasks = String.valueOf(tasks.size());
            System.out.println("Got it I have added this task:" + "\n" +  currTask);
            System.out.println(updateNumMessage(numTasks));
            System.out.println(horizontal_line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an "event" task to the task list and provides user feedback.
     *
     * @param userCommand The user input command string.
     * @param tasks The current list of tasks.
     */
    public static void handleEvent(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ", 2);
            String description = "";
            String fromDate = "";
            String byDate = "";
            if (parts.length >= 2) {
                String[] secondPartSplits = parts[1].split(" /from ", 2);
                if (secondPartSplits.length >= 2) {
                    String[] dates = secondPartSplits[1].split(" /to ", 2);
                    if (dates.length >= 2) {
                        fromDate = dates[0].trim();
                        byDate = dates[1].trim();
                        description = secondPartSplits[0];
                    }
                }
            }
            Task currTask = new Events(description, fromDate, byDate);
            tasks.add(currTask);
            String numTasks = String.valueOf(tasks.size());
            System.out.println("Got it I have added this task:" + "\n" +  currTask);
            System.out.println(updateNumMessage(numTasks));
            System.out.println(horizontal_line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the scanner and sends a bye message to the user.
     *
     * @param scanner The scanner used for user input.
     */
    public static void handleBye(Scanner scanner) {
        scanner.close();
        System.out.println(horizontal_line);
        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }
}