import java.util.Scanner;
import java.util.ArrayList;

/**
 * A chatbot named Bert that keeps track of a task list.
 */
public class Bert {
    /**
     * botName stores the name of the chatbot.
     */
    private static final String botName = "Bert";
    /**
     * al stores the list of tasks.
     */
    private static ArrayList<Task> al = new ArrayList<>();

    /**
     * Introduces itself.
     */
    private static void introduce() {
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm " + Bert.botName + "\n" +
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
                "Here are the tasks in your list:\n"
        );
        for (int i = 0; i < al.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + al.get(i) + "\n");
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
        if (description.length() == 0) {
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
        if (substring.length() == 0) {
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
        if (substring.length() == 0) {
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
                    s = sc.nextLine().trim();
                    // Typing 'mark x' marks a task at a specific index on the list
                } else if (cmd.equals("mark")) {
                    int i = Integer.parseInt(s.substring(indexOfFirstSpace + 1)) - 1;
                    Bert.mark(i);
                    s = sc.nextLine();
                    // Typing 'unmark x' unmarks a task at a specific index on the list
                } else if (cmd.equals("unmark")) {
                    int i = Integer.parseInt(s.substring(indexOfFirstSpace + 1)) - 1;
                    Bert.unmark(i);
                    s = sc.nextLine();
                    // Typing 'todo...' stores a todo task
                } else if (cmd.equals("todo")) {
                    String toDoTask = s.substring(indexOfFirstSpace + 1);
                    Bert.todo(toDoTask);
                    s = sc.nextLine().trim();
                    // Typing 'deadline...' stores a deadline task
                } else if (cmd.equals("deadline")) {
                    String remainder = s.substring(indexOfFirstSpace + 1);
                    Bert.deadline(remainder);
                    s = sc.nextLine().trim();
                    // Typing 'event...' stores an event task
                } else if (cmd.equals("event")) {
                    String remainder = s.substring(indexOfFirstSpace + 1);
                    Bert.event(remainder);
                    s = sc.nextLine().trim();
                    // Any other text that the user inputs is read and stored as a task
                } else {
                    throw new BertInvalidTaskException();
                }
            } catch (BertEmptyTaskException e) {
                System.out.println(
                        "____________________________________________________________" +
                        "OOPS!!! " + e.getMessage() +
                        "____________________________________________________________"
                );
            } catch (BertInvalidTaskException e) {
                System.out.println(
                        "____________________________________________________________" +
                        "OOPS!!! " + e.getMessage() +
                        "____________________________________________________________"
                );
            }
        }

        sc.close();
        Bert.exit();
    }
}
