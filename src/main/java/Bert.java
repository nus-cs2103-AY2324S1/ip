import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

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
     * @param index The index of a task on the list
     */
    private static void mark(String index) {
        int i = Integer.parseInt(index) - 1;
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
     * @param index The index of a task on the list
     */
    private static void unmark(String index) {
        int i = Integer.parseInt(index) - 1;
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
     * @param index The index of a task on the list
     */
    private static void delete(String index) {
        int i = Integer.parseInt(index) - 1;
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

    /**
     * Loads tasks from the save data into the chatbot.
     */
    private static void loadTasks() {
        try {
            ensureTaskFileExists();
            File file = new File(FILE_PATH);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                switch (task.charAt(0)) {
                    case 'T':
                        ToDo t = ToDo.createFromSaveFormat(task);
                        al.add(t);
                        break;
                    case 'D':
                        Deadline d = Deadline.createFromSaveFormat(task);
                        al.add(d);
                        break;
                    case 'E':
                        Event e = Event.createFromSaveFormat(task);
                        al.add(e);
                        break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! The attempt to open the file has failed.\n" +
                    "____________________________________________________________\n"
            );
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while creating the task file.\n" +
                    "____________________________________________________________\n"
            );
        }
    }

    public static void main(String[] args) {
        Bert.introduce();
        Bert.loadTasks();

        // Initialise a scanner and read the first line of user input
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        // The program runs until the user enters 'bye'
        while (!s.equals("bye")) {
            try {
                String cmd;
                String input = "";
                int indexOfFirstSpace = s.indexOf(" ");
                if (indexOfFirstSpace == -1) {
                    cmd = s;
                } else {
                    cmd = s.substring(0, indexOfFirstSpace);
                    input = s.substring(indexOfFirstSpace + 1);
                }

                switch (cmd) {
                    // Typing 'list' prints out the list of tasks
                    case "list":
                        Bert.list();
                        break;
                    // Typing 'mark x' marks a task at a specific index on the list
                    case "mark":
                        Bert.mark(input);
                        break;
                    // Typing 'unmark x' unmarks a task at a specific index on the list
                    case "unmark":
                        Bert.unmark(input);
                        break;
                    // Typing 'todo...' stores a todo task
                    case "todo":
                        Bert.todo(input);
                        break;
                    // Typing 'deadline...' stores a deadline task
                    case "deadline":
                        Bert.deadline(input);
                        break;
                    // Typing 'event...' stores an event task
                    case "event":
                        Bert.event(input);
                        break;
                    // Typing delete deletes a task
                    case "delete":
                        Bert.delete(input);
                        break;
                    default:
                        throw new BertInvalidTaskException();
                }
            } catch (BertException e) {
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
