import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.ShibaTask;
import tasks.TodoTask;

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

    private static final ShibaTask[] tasks = new ShibaTask[100];
    private static int taskCount = 0;

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
     */
    private static void printInvalidCommand() {
        printHorizontalLine();
        printWithLevel2Indent("Woof! That command is invalid! You're missing some parameters.");
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
                case BYE_COMMAND:
                    return;
                default:
                    printUnknownCommand();
            }
        }
    }

    /**
     * Adds a new task to the list.
     * @param cmd The full command to be parsed into a task.
     */
    private static void addTodo(String cmd) {
        TodoTask todo = TodoTask.fromCmd(cmd);

        if (todo == null) {
            printInvalidCommand();
            return;
        }

        addTask(todo);
    }

    /**
     * Adds a new deadline task to the list.
     * @param cmd The full command to be parsed into a task.
     */
    private static void addDeadline(String cmd) {
        DeadlineTask deadline = DeadlineTask.fromCmd(cmd);

        if (deadline == null) {
            printInvalidCommand();
            return;
        }

        addTask(deadline);
    }

    /**
     * Adds a new event to the list.
     * @param cmd The full command to be parsed into a task.
     */
    private static void addEvent(String cmd) {
        EventTask event = EventTask.fromCmd(cmd);

        if (event == null) {
            printInvalidCommand();
            return;
        }

        addTask(event);
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    private static void addTask(ShibaTask task) {
        tasks[taskCount] = task;
        taskCount++;

        printHorizontalLine();
        printWithLevel2Indent("Woof! I've added this task:");
        printWithLevel3Indent(task.toString());
        printWithLevel2Indent("You now have " + taskCount + " tasks in the list. Now gimme some treats.");
        printHorizontalLine();
    }

    /**
     * Lists all the tasks in the list.
     */
    private static void listTasks() {
        printHorizontalLine();
        for (int i = 0; i < taskCount; i++) {
            printWithLevel2Indent((i + 1) + "." + tasks[i]);
        }
        if (taskCount == 0) {
            printWithLevel2Indent("Woof! You have no tasks in the list - go browse some Reddit!");
        }
        printHorizontalLine();
    }

    /**
     * Performs actions to mark/unmark a task based on the input command parameters
     * @param cmd Input command parameters, split by spaces.
     */
    private static void handleMarkTask(String[] cmd) {
        if (cmd.length < 2) {
            printHorizontalLine();
            printWithLevel2Indent("Woof! Please specify a task number!");
            printHorizontalLine();
            return;
        }

        int taskNumber = Integer.parseInt(cmd[1]);
        if (taskNumber < 1 || taskNumber > taskCount) {
            printHorizontalLine();
            printWithLevel2Indent("Woof! Please specify a valid task number!");
            printHorizontalLine();
            return;
        }

        ShibaTask task = tasks[taskNumber - 1];
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
