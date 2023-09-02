import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
     * An array to store inputs by the user
     */
    private static ArrayList<Task> taskArray = new ArrayList<>();
    private static int numOfTasks = 0;
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
        int count = 1;
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
     * Converts the string into a ToDo object
     * then appends it to the task array
     *
     * @param task description of task
     */
    private static void appendToDo(String task) {
        try {
            String todo = task.substring(5);
            append(new ToDo(todo));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "todo {task}\n" +
                    horizontalLine);
        }
    }

    /**
     * Converts the string into a Deadline object
     * then appends it to the task array
     *
     * @param task description of task with 'by' time
     */
    private static void appendDeadline(String task) {
        try {
            String deadline = task.substring(9);
            int splitPoint = deadline.indexOf(" /by ");
            append(new Deadline(deadline.substring(0, splitPoint),
                    deadline.substring(splitPoint + 5)));
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
     * Converts the string into an Event object
     * then appends it to the task array
     *
     * @param task description of task with 'from' time and 'to' time
     */
    private static void appendEvent(String task) {
        try {
            String event = task.substring(6);
            int startPoint = event.indexOf(" /from ");
            int endPoint = event.indexOf(" /to ");
            append(new Event(event.substring(0, startPoint),
                    event.substring(startPoint + 7, endPoint),
                    event.substring(endPoint + 5)));
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
            Task task = taskArray.get(Integer.parseInt(toMark.substring(5)) - 1);
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
            int index = Integer.parseInt(toUnmark.substring(7)) - 1;
            Task task = taskArray.get(index);
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
            int index = Integer.parseInt(toDelete.substring(7)) - 1;
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
            if (nextLine.startsWith("mark")) {
                mark(nextLine);
                continue;
            }
            if (nextLine.startsWith("unmark")) {
                unmark(nextLine);
                continue;
            }
            if (nextLine.startsWith("todo")) {
                appendToDo(nextLine);
                continue;
            }
            if (nextLine.startsWith("deadline")) {
                appendDeadline(nextLine);
                continue;
            }
            if (nextLine.startsWith("event")) {
                appendEvent(nextLine);
                continue;
            }
            if (nextLine.startsWith("delete")) {
                delete(nextLine);
                continue;
            }
            switch(nextLine) {
                case "bye":
                    botStatus = Status.STOPPING;
                    break;
                case "list":
                    list();
                    break;
                default:
                    append(new Task(nextLine));
            }
        }

        taskStorage.write();
        exit();
    }
}
