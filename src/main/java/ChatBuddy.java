import java.util.ArrayList;
import java.util.Scanner;

public class ChatBuddy {
    // store a list of tasks
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Add task to list array.
     *
     * @param task The task to add into the list of tasks.
     */
    private static void addTask(Task task) {
        // add task to list
        list.add(task);

        // display message
        printHorizontalLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.toString());
        System.out.println(String.format("    Now you have %d tasks in the list.", list.size()));
        printHorizontalLine();
    }

    /**
     * Add a todo task into the task list.
     *
     * @param userInput The input provided by the user that starts with "todo".
     * @throws ChatBuddyException
     */
    private static void addToDo(String userInput) throws ChatBuddyException {
        if (userInput.trim().length() <= 4) {
            // no description given
            // trim userInput to get rid of potential whitespace at the back of the user input
            throw new ChatBuddyException("The description of a todo cannot be empty.");
        }
        String taskDescription = userInput.substring(5);
        ToDo todo = new ToDo(taskDescription);
        addTask(todo);
    }

    /**
     * Add a deadline task into the task list.
     *
     * @param userInput The input provided by the user that starts with "deadline".
     *                  The userInput should include /by.
     * @throws ChatBuddyException
     */
    private static void addDeadline(String userInput) throws ChatBuddyException {
        int byCommandIndex = userInput.indexOf("/by");
        if (byCommandIndex == -1) {
            // check if /by is in the input
            throw new ChatBuddyException("Deadlines need to include \"/by\" followed by the deadline.");
        } else if (byCommandIndex - 1 < 9) {
            // no description given
            throw new ChatBuddyException("The description of a deadline cannot be empty.");
        }

        String taskDescription = userInput.substring(9, byCommandIndex - 1);
        if (byCommandIndex + 4 >= userInput.length()) {
            throw new ChatBuddyException("The deadline cannot be empty.");
        }
        String by = userInput.substring(byCommandIndex + 4);
        if (by.trim().equals("")) {
            throw new ChatBuddyException("The deadline cannot be empty.");
        }

        Deadline deadline = new Deadline(taskDescription, by);
        addTask(deadline);
    }

    /**
     * Add an event task into the task list.
     *
     * @param userInput The input provided by the user that starts with "event".
     *                  The userInput should include /from and /to.
     * @throws ChatBuddyException
     */
    private static void addEvent(String userInput) throws ChatBuddyException {
        int fromIndex = userInput.indexOf("/from");
        if (fromIndex == -1) {
            // check if /by is in the input
            throw new ChatBuddyException("Events need to include \"/from\" followed by the start date/time.");
        } else if (fromIndex - 1 < 6) {
            // no description given
            throw new ChatBuddyException("The description of a deadline cannot be empty.");
        }

        int toIndex = userInput.indexOf("/to");
        if (toIndex == -1) {
            // check if /by is in the input
            throw new ChatBuddyException("Events need to include \"/to\" followed by the end date/time.");
        }

        String taskDescription = userInput.substring(6, fromIndex - 1);
        String from = userInput.substring(fromIndex + 6, toIndex - 1);
        if (from.trim().equals("")) {
            throw new ChatBuddyException("The start date/time of an event cannot be empty.");
        }

        if (toIndex + 4 >= userInput.length()) {
            throw new ChatBuddyException("The end date/time of an event cannot be empty.");
        }
        String to = userInput.substring(toIndex + 4);
        if (to.trim().equals("")) {
            throw new ChatBuddyException("The end date/time of an event cannot be empty.");
        }
        Event event = new Event(taskDescription, from, to);
        addTask(event);
    }

    /**
     * Method to delete a task from the list.
     *
     * @param userInput The input provided by the user.
     * @throws ChatBuddyException if the task number inputted by the user is not a valid task number.
     */
    private static void deleteTask(String userInput) throws ChatBuddyException {
        String indexString = userInput.substring(7);
        int taskIndex = Integer.parseInt(indexString) - 1;
        if (taskIndex >= list.size()) {
            throw new ChatBuddyException("Please input a valid task number. There are only " +
                    list.size() + " tasks in the list.");
        }
        Task task = list.remove(taskIndex);
        printHorizontalLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task.toString());
        System.out.println(String.format("    Now you have %d tasks in the list.", list.size()));
        printHorizontalLine();
    }

    /** Displays the list of tasks. */
    private static void printTaskList() {
        printHorizontalLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task == null) {
                // there are no more tasks
                break;
            }
            System.out.println(String.format("    %1s.%2s", i + 1, task.toString()));
        }
        printHorizontalLine();
    }

    /** Displays a horizontal line. */
    private static void printHorizontalLine() {
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHorizontalLine();
        System.out.println("    Hello! I'm Chat Buddy!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    printTaskList();
                } else if (userInput.matches("mark [1-9][0-9]*")) {
                    String indexString = userInput.substring(5);
                    int taskIndex = Integer.parseInt(indexString) - 1;
                    if (taskIndex >= list.size()) {
                        throw new ChatBuddyException("Please input a valid task number. There are only " +
                                list.size() + " tasks in the list.");
                    }
                    Task task = list.get(taskIndex);
                    task.markAsDone();
                } else if (userInput.matches("unmark [1-9][0-9]*")) {
                    String indexString = userInput.substring(7);
                    int taskIndex = Integer.parseInt(indexString) - 1;
                    if (taskIndex >= list.size()) {
                        throw new ChatBuddyException("Please input a valid task number. There are only " +
                                list.size() + " tasks in the list.");
                    }
                    Task task = list.get(taskIndex);
                    task.markAsNotDone();
                } else if (userInput.startsWith("todo")) {
                    addToDo(userInput);
                } else if (userInput.startsWith("deadline")) {
                    addDeadline(userInput);
                } else if (userInput.startsWith("event")) {
                    addEvent(userInput);
                } else if (userInput.matches("delete [1-9][0-9]*")) {
                    deleteTask(userInput);
                } else {
                    throw new ChatBuddyException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (ChatBuddyException e) {
                printHorizontalLine();
                System.out.println("     " + e.toString());
                printHorizontalLine();
            }
            userInput = scanner.nextLine();
        }

        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
