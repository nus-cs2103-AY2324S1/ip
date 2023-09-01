import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A chatbot named Bert that keeps track of a task list.
 */
public class Bert {
    private static final String BOT_NAME = "Bert";
    private static final String FILE_PATH = "./data/bert.txt";
    private static ArrayList<Task> al = new ArrayList<>();

    /**
     * Introduces itself.
     */
    private static void introduce() {
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm " + Bert.BOT_NAME + "\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Sends exit message.
     */
    private static void exit() {
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Prints out the list of tasks.
     */
    private static void list() {
        System.out.println(
                "____________________________________________________________\n" +
                "Here are the tasks in your list:"
        );
        for (int i = 0; i < al.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + al.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Marks a task at a specific index of the list as done.
     *
     * @param i The index of a task on the list
     */
    private static void mark(int i) {
        Task t = al.get(i);
        t.markAsDone();
        al.set(i, t);
        System.out.println(
                "____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "  " + t + "\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Marks a task at a specific index of the list as undone.
     *
     * @param i The index of a task on the list
     */
    private static void unmark(int i) {
        Task t = al.get(i);
        t.markAsUndone();
        al.set(i, t);
        System.out.println(
                "____________________________________________________________\n" +
                "OK, I've marked this task as not done yet:\n" +
                "  " + t + "\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Adds a todo task to the list, and prints out the size of list thereafter.
     *
     * @param description The description of the task that follows the todo command
     * @throws BertEmptyTaskException This exception is thrown when the task description is empty
     */
    private static void todo(String description) throws BertEmptyTaskException {
        if (description.isBlank()) {
            throw new BertEmptyTaskException();
        }
        ToDo t = new ToDo(description);
        al.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "  " + t + "\n" +
                "Now you have " + al.size() + " tasks in the list.\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Adds a deadline task to the list, and prints out the size of list thereafter.
     *
     * @param substring The substring that follows the deadline command
     * @throws BertEmptyTaskException This exception is thrown when the task description is empty
     */
    private static void deadline(String substring) throws BertEmptyTaskException {
        if (substring.isBlank()) {
            throw new BertEmptyTaskException();
        }
        String[] inputs = substring.split(" /by ");
        Deadline t = new Deadline(inputs[0], inputs[1]);
        al.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "  " + t + "\n" +
                "Now you have " + al.size() + " tasks in the list.\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Adds an event task to the list, and prints out the size of list thereafter.
     *
     * @param substring The substring that follows the event command
     * @throws BertEmptyTaskException This exception is thrown when the task description is empty
     */
    private static void event(String substring) throws BertEmptyTaskException {
        if (substring.isBlank()) {
            throw new BertEmptyTaskException();
        }
        String[] descriptionAndTimes = substring.split(" /from ");
        String[] times = descriptionAndTimes[1].split(" /to ");
        Event t = new Event(descriptionAndTimes[0], times[0], times[1]);
        al.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "  " + t + "\n" +
                "Now you have " + al.size() + " tasks in the list.\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Deletes the task at the specific index of the list.
     *
     * @param i The index of a task on the list
     */
    private static void delete(int i) {
        Task t = al.remove(i);
        System.out.println(
                "____________________________________________________________\n" +
                "Noted. I've removed this task:\n" +
                "  " + t + "\n" +
                "Now you have " + al.size() + " tasks in the list.\n" +
                "____________________________________________________________\n"
        );
    }

    /**
     * Saves the task list into ip/data/bert.txt.
     */
    private static void saveTasks() {
        try {
            ensureTaskFileExists();
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while creating the task file.\n" +
                    "____________________________________________________________\n"
            );
            return;
        }

        try {
            Bert.writeToFile(FILE_PATH, Bert.taskListToSaveFormat());
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while saving tasks.\n" +
                    "____________________________________________________________\n"
            );
        }
    }

    /**
     * Checks if ip/data/bert.txt exists.
     * If the directory or the file does not exist,
     * creates the directory and the file.
     *
     * @throws IOException This exception is thrown when an error occurs while creating
     *          the file.
     */
    private static void ensureTaskFileExists() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.getParentFile().isDirectory()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Converts the task list into its save format.
     *
     * @return The String representation of the formatted task list
     */
    private static String taskListToSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task t : al) {
            sb.append(t.toSaveFormat() + System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Writes the textToAdd input into a file specified by filePath.
     *
     * @param filePath The path to the file to be written
     * @param textToAdd The String of text to be written in the file
     * @throws IOException This exception is thrown when an error occurs while opening
     *          the file.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        Bert.introduce();

        // Initialise a scanner and read the first line of user input
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        // The program runs until the user enters 'bye'
        while (!s.equals("bye")) {
            try {
                String cmd;
                int indexOfFirstSpace = s.indexOf(" ");
                if (indexOfFirstSpace == -1) {
                    cmd = s;
                } else {
                    cmd = s.substring(0, indexOfFirstSpace);
                }
                // Typing 'list' prints out the list of tasks
                if (cmd.equals("list")) {
                    Bert.list();
                    // Typing 'mark x' marks a task at a specific index on the list
                } else if (cmd.equals("mark")) {
                    int i = Integer.parseInt(s.substring(indexOfFirstSpace + 1)) - 1;
                    Bert.mark(i);
                    // Typing 'unmark x' unmarks a task at a specific index on the list
                } else if (cmd.equals("unmark")) {
                    int i = Integer.parseInt(s.substring(indexOfFirstSpace + 1)) - 1;
                    Bert.unmark(i);
                    // Typing 'todo...' stores a todo task
                } else if (cmd.equals("todo")) {
                    String remainder;
                    if (indexOfFirstSpace == -1) {
                        remainder = "";
                    } else {
                        remainder = s.substring(indexOfFirstSpace + 1);
                    }
                    Bert.todo(remainder);
                    // Typing 'deadline...' stores a deadline task
                } else if (cmd.equals("deadline")) {
                    String remainder;
                    if (indexOfFirstSpace == -1) {
                        remainder = "";
                    } else {
                        remainder = s.substring(indexOfFirstSpace + 1);
                    }
                    Bert.deadline(remainder);
                    // Typing 'event...' stores an event task
                } else if (cmd.equals("event")) {
                    String remainder;
                    if (indexOfFirstSpace == -1) {
                        remainder = "";
                    } else {
                        remainder = s.substring(indexOfFirstSpace + 1);
                    }
                    Bert.event(remainder);
                    // Typing delete deletes a task
                } else if (cmd.equals("delete")) {
                    int i = Integer.parseInt(s.substring(indexOfFirstSpace + 1)) - 1;
                    Bert.delete(i);
                } else {
                    throw new BertInvalidTaskException();
                }
            } catch (BertEmptyTaskException e) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "OOPS!!! " + e.getMessage() + "\n" +
                        "____________________________________________________________\n"
                );
            } catch (BertInvalidTaskException e) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "OOPS!!! " + e.getMessage() + "\n" +
                        "____________________________________________________________\n"
                );
            } finally {
                s = sc.nextLine().trim();
            }
        }

        sc.close();
        Bert.saveTasks();
        Bert.exit();
    }
}
