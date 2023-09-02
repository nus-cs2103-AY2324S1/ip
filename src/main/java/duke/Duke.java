package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The main class controlling the chatbot's actions.
 * This class should be able to keep track of tasks, and
 * send messages to the user.
 */
public class Duke {
    /**
     * Common text elements to be reused by the chatbot, such as the chatbot name,
     * and the horizontal line element.
     */
    private static final String name = "JOHNATHAN CENATOR\n";
    private static final String horizontalLine = "-------------------------------\n";

    /**
     * Store tasks in a separate class
     */
    private static Storage taskStorage = new Storage();

    /**
     * An enum to track the status of the chatbot
     * RUNNING, STOPPING, etc.
     * [To add more if needed]
     */
    enum Status {RUNNING, STOPPING}

    /**
     * Sends a greeting message on startup of the chatbot.
     */
    private static void greet() {
        System.out.print(horizontalLine +
                "YOU DIDN'T SEE\n" +
                name +
                "COMING DID YOU!?\n" +
                horizontalLine);
    }

    /**
     * Sends a departing message on chatbot shutdown.
     */
    private static void exit() {
        System.out.print(horizontalLine +
                "NOW GET OUTTA HERE!\n" +
                "RESPECTFULLY,\n" +
                name +
                horizontalLine);
    }

    /**
     * Repeats the user's input
     *
     * @param input the user's text input
     */
    private static void echo(String input) {
        System.out.print(horizontalLine + input + "\n" + horizontalLine);
    }

    /**
     * Lists all tasks in the task array
     */
    private static void list() {
        System.out.print(horizontalLine);
        System.out.print(taskStorage.list());
        System.out.print(horizontalLine);
    }

    /**
     * Appends a task to the task array
     *
     * @param task The task inputted by the user
     */
    private static void append(Task task) {
        taskStorage.appendTask(task);
        System.out.print(horizontalLine + "YOU WANT TO " + task + "?\nSURE, WHATEVER.\n" + horizontalLine);
    }

    /**
     * Converts the string into a duke.ToDo object
     * then appends it to the task array
     *
     * @param task description of task
     */
    private static void appendToDo(String task) {
        try {
            append(new ToDo(Parser.parseToDo(task)));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "todo {task}\n" +
                    horizontalLine);
        }
    }

    /**
     * Converts the string into a duke.Deadline object
     * then appends it to the task array
     *
     * @param task description of task with 'by' time
     */
    private static void appendDeadline(String task) {
        try {
            String[] parsedDeadline = Parser.parseDeadline(task);
            append(new Deadline(parsedDeadline[0], parsedDeadline[1]));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "deadline {task} /by {time}\n" +
                    horizontalLine);
        } catch (DateTimeParseException e) {
            System.out.print(horizontalLine + "Date format should be yyyy-mm-dd\n"
                    + horizontalLine);
        }
    }

    /**
     * Converts the string into an duke.Event object
     * then appends it to the task array
     *
     * @param task description of task with 'from' time and 'to' time
     */
    private static void appendEvent(String task) {
        try {
            String[] parsedEvent = Parser.parseEvent(task);
            append(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "event {task} /from {time} /to {time}\n" +
                    horizontalLine);
        } catch (DateTimeParseException e) {
            System.out.print(horizontalLine + "Date format should be yyyy-mm-dd\n"
                    + horizontalLine);
        }
    }
    /**
     * Attempts to mark a task in the task array
     *
     * @param toMark
     */
    private static void mark(String toMark) {
        System.out.print(horizontalLine);
        try {
            Task task = taskStorage.get(Parser.parseMark(toMark));
            if (task == null) throw new NullPointerException();
            if (task.isDone) throw new IllegalArgumentException();
            task.markAsDone();
            System.out.println("MARKED:\n" + task);
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (NullPointerException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("ALREADY DONE BRO!\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    /**
     * Attempts to unmark a task in the task array
     *
     * @param toUnmark the task to be unmarked
     */
    private static void unmark(String toUnmark) {
        System.out.print(horizontalLine);
        try {
            Task task = taskStorage.get(Parser.parseUnmark(toUnmark));
            if (task == null) throw new NullPointerException();
            if (!task.isDone) throw new IllegalArgumentException();
            task.markAsUndone();
            System.out.println("UNMARKED:\n" + task);
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (NullPointerException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("ALREADY UNDONE BRO!\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    /**
     * Attempts to delete a task from the task array
     * @param toDelete
     */
    private static void delete(String toDelete) {
        System.out.print(horizontalLine);
        try {
            int index = Parser.parseDelete(toDelete);
            System.out.print("YOU SEE THIS?\n" +
                    taskStorage.get(index) +
                    "\nNOW YOU DON'T\n");
            taskStorage.delete(index);
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("YOU WANT ME TO DELETE THE AIR???\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    public static void main(String[] args) throws IOException {
        greet();
        Scanner textInput = new Scanner(System.in);
        Status botStatus = Status.RUNNING;


        while (botStatus == Status.RUNNING) {
            String nextLine = textInput.nextLine();
            Parser.ParserOutput signal = Parser.parseInput(nextLine);
            switch (signal) {
            case MARK:
                mark(nextLine);
                continue;
            case UNMARK:
                unmark(nextLine);
                continue;
            case DELETE:
                delete(nextLine);
                continue;
            case LIST:
                list();
                continue;
            case ECHO:
                echo(nextLine);
                continue;
            case EXIT:
                botStatus = Status.STOPPING;
                continue;
            case APPEND:
                Parser.TaskType type = Parser.parseTask(nextLine);
                switch (type) {
                case TODO:
                    appendToDo(nextLine);
                case EVENT:
                    appendEvent(nextLine);
                case DEADLINE:
                    appendEvent(nextLine);
                case GENERIC:
                    append(new Task(nextLine));
                }
            }
        }

        taskStorage.write();
        exit();
    }
}
