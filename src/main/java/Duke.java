import exceptions.EmptyTasksException;
import exceptions.InvalidCommandException;
import exceptions.ShibaException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.ShibaTask;
import tasks.TodoTask;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORZ_LINE = "____________________________________________________________";
    private static final String BOT_NAME = "SHIBA-BOT";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    private static final ArrayList<ShibaTask> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printGreeting();
        processUserInputs();
        printBye();
    }

    /**
     * Prints the greeting message.
     */
    private static void printGreeting() {
        printHorizontalLine();
        printWithLevel2Indent("Woof! I'm " + BOT_NAME);
        printWithLevel2Indent("What can I bark at you?");
        printHorizontalLine();
    }

    /**
     * Prints the bye message
     */
    private static void printBye() {
        printHorizontalLine();
        printWithLevel2Indent("Woof! Hope to bark at you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints the unknown command message.
     */
    private static void printUnknownCommand() {
        printHorizontalLine();
        printWithLevel2Indent("Woof! I don't know what that command is!");
        printHorizontalLine();
    }

    /**
     * Prints the invalid command message.
     * @param e The ShibaException whose message is to be printed.
     */
    private static void printException(ShibaException e) {
        printHorizontalLine();
        printWithLevel2Indent("Woof! " + e.getMessage());
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line.
     */
    private static void printHorizontalLine() {
        printWithLevel1Indent(HORZ_LINE);
    }

    /**
     * Prints the given message with a single tab indent (4 spaces).
     * @param message The message to be printed.
     */
    private static void printWithLevel1Indent(String message) {
        printWithIndents(message, 1, 0);
    }

    /**
     * Prints the given message with a single tab indent and 1 space.
     * @param message The message to be printed.
     */
    private static void printWithLevel2Indent(String message) {
        printWithIndents(message, 1, 1);
    }

    /**
     * Prints the given message with a single tab indent and 2 spaces.
     * @param message The message to be printed.
     */
    private static void printWithLevel3Indent(String message) {
        printWithIndents(message, 1, 3);
    }

    /**
     * Prints the given message with the given number of indents (spaces).
     * @param message The message to be printed.
     * @param tabs The number of tab indents (4 spaces each).
     * @param spaces The number of spaces indents (in addition to tabs).
     */
    private static void printWithIndents(String message, int tabs, int spaces) {
        System.out.println(" ".repeat(tabs * 4 + spaces) + message);
    }

    /**
     * Continually processes user input until bye command is issued
     */
    private static void processUserInputs() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().strip();
            String[] cmd = input.split(" ");

            try {
                switch (cmd[0]) {
                    case LIST_COMMAND:
                        listTasks();
                        break;
                    case MARK_COMMAND:
                    case UNMARK_COMMAND:
                        handleMarkTask(cmd);
                        break;
                    case TODO_COMMAND:
                        addTodo(input);
                        break;
                    case DEADLINE_COMMAND:
                        addDeadline(input);
                        break;
                    case EVENT_COMMAND:
                        addEvent(input);
                        break;
                    case DELETE_COMMAND:
                        deleteTask(cmd);
                        break;
                    case BYE_COMMAND:
                        return;
                    default:
                        printUnknownCommand();
                }
            } catch (ShibaException e) {
                printException(e);
            }
        }
    }

    /**
     * Adds a new task to the list.
     * @param cmd The full command to be parsed into a task.
     * @throws ShibaException If the command has missing parameters.
     */
    private static void addTodo(String cmd) throws ShibaException {
        TodoTask todo = TodoTask.fromCmd(cmd);
        addTask(todo);
    }

    /**
     * Adds a new deadline task to the list.
     * @param cmd The full command to be parsed into a task.
     * @throws ShibaException If the command has missing parameters.
     */
    private static void addDeadline(String cmd) throws ShibaException {
        DeadlineTask deadline = DeadlineTask.fromCmd(cmd);
        addTask(deadline);
    }

    /**
     * Adds a new event to the list.
     * @param cmd The full command to be parsed into a task.
     * @throws ShibaException If the command has missing parameters.
     */
    private static void addEvent(String cmd) throws ShibaException {
        EventTask event = EventTask.fromCmd(cmd);
        addTask(event);
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    private static void addTask(ShibaTask task) {
        tasks.add(task);

        printHorizontalLine();
        printWithLevel2Indent("Woof! I've added this task:");
        printWithLevel3Indent(task.toString());
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        printWithLevel2Indent("You now have " + tasks.size() + taskWord + " in the list. Now gimme some treats.");
        printHorizontalLine();
    }

    /**
     * Checks if the task number is valid.
     * @param cmd The command parameters, split by spaces.
     * @throws ShibaException If the task number is missing, invalid, or there are no tasks in the list.
     * @return The task number if valid.
     */
    private static int checkTaskNumber(String[] cmd) throws ShibaException {
        if (cmd.length < 2) {
            throw new InvalidCommandException("Please specify a task number!");
        }

        try {
            int taskNumber = Integer.parseInt(cmd[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                if (taskNumber > tasks.size() && tasks.isEmpty()) {
                    throw new EmptyTasksException();
                }
                throw new InvalidCommandException("Please specify a valid task number!");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number! Please enter a positive integer.");
        }
    }

    /**
     * Deletes a task from the list.
     * @param cmd The command parameters, split by spaces.
     * @throws ShibaException If the task number is missing or invalid.
     */
    private static void deleteTask(String[] cmd) throws ShibaException {
        int taskNumber = checkTaskNumber(cmd);
        ShibaTask task = tasks.remove(taskNumber - 1);

        printHorizontalLine();
        printWithLevel2Indent("Woof! I've deleted this task:");
        printWithLevel3Indent(task.toString());
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        printWithLevel2Indent("You now have " + tasks.size() + taskWord + " in the list. Some headpats please?");
        printHorizontalLine();
    }

    /**
     * Lists all the tasks in the list.
     */
    private static void listTasks() {
        printHorizontalLine();
        for (int i = 0; i < tasks.size(); i++) {
            printWithLevel2Indent((i + 1) + "." + tasks.get(i));
        }
        if (tasks.isEmpty()) {
            printWithLevel2Indent("Woof! You have no tasks in the list - go browse some Reddit!");
        }
        printHorizontalLine();
    }

    /**
     * Performs actions to mark/unmark a task based on the input command parameters
     * @param cmd Input command parameters, split by spaces.
     * @throws ShibaException If the task number is missing or invalid.
     */
    private static void handleMarkTask(String[] cmd) throws ShibaException {
        int taskNumber = checkTaskNumber(cmd);

        ShibaTask task = tasks.get(taskNumber - 1);
        if (cmd[0].equals(MARK_COMMAND)) {
            boolean res = task.markDone();
            printHorizontalLine();
            if (res) {
                printWithLevel2Indent("Woof! I've marked this task as done:");
            } else {
                printWithLevel2Indent("Woof! This task is already done!");
            }
            printWithLevel3Indent(task.toString());
            printHorizontalLine();
        } else {
            boolean res = task.markNotDone();
            printHorizontalLine();
            if (res) {
                printWithLevel2Indent("Woof! I've marked this task as not done yet:");
            } else {
                printWithLevel2Indent("Woof! You have not done this task yet!");
            }
            printWithLevel3Indent(task.toString());
            printHorizontalLine();
        }
    }
}
