import java.util.Scanner;

/**
 * This class encapsulates the skeleton of the nyanbot.
 *
 * @author Tan Kerway
 */
public class Duke {
    // array to store the user's items, as a Task object
    private final Task[] items;
    // pointer that will always point to the last element of the array
    private int i;

    /**
     * Constructs an instance of a chatbot class.
     *
     * @author Tan Kerway
     */
    public Duke() {
        this.items = new Task[100];
        this.i = -1;
    }

    /**
     * When called, the chatbot will greet the user.
     *
     * @author Tan Kerway
     */
    private void greet() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Hello! I'm nyancatbot!\nWhat can I do for nyan?");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Prints the current list of tasks.
     *
     * @author Tan Kerway
     */
    void listAllTasks() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list :3");
        int index = -1;
        while(this.items[++index] != null) {
            System.out.println((index + 1) + ". " + this.items[index].toString());
        }
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Adds a task to the local task list. Handles the three different types
     * class.
     *
     * @author Tan Kerway
     * @param task the task to be added to the current list of tasks
     * @return the task object created that was just added
     */
    private Task addTask(String task) {
        String description;
        Task createdTask;
        // handle the 3 different types of class
        if (task.startsWith("todo")) {
            description = task.substring(5);
            createdTask = new Todo(description);
        } else if (task.startsWith("deadline")) {
            // cache the start index of the "/by" substring
            int indexOfBy = task.indexOf("/by");
            // get the task description
            description = task.substring(9, indexOfBy - 1);
            // get the deadline
            String by = task.substring(indexOfBy + 4);
            createdTask = new Deadline(description, by);
        } else {
            int fromStart = task.lastIndexOf("/from");
            // get the task description
            description = task.substring(6, fromStart - 1);
            // get the string that holds the start and the end
            String period = task.substring(fromStart);
            createdTask = new Event(description, period);
        }
        this.items[++this.i] = createdTask;
        return createdTask;
    }

    /**
     * Echos the task that was added to the task list to
     * the console.
     *
     * @author Tan Kerway
     * @param task the task to be echoed to the console
     */
    void echoTaskAdded(Task task) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n    " + task.toString());
        System.out.println("Nyan you have " + (this.i + 1) + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     */
    private void handleMarkTask(Task currentTask){
        currentTask.markDone();
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     */
    private void echoTaskMarked(Task currentTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as nyan:");
        System.out.println("    " + currentTask);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Marks a task as done.
     *
     * @author Tan Kerway
     * @param currentTask the task to be marked as done
     */
    private void handleUnmarkTask(Task currentTask){
        currentTask.markUndone();
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     */
    private void echoTaskUnmarked(Task currentTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not nyan yet:");
        System.out.println("    " + currentTask);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Processes user commands. Breaks down the input and
     * Checks which command the user types in.
     *
     * @author Tan Kerway
     * @param input the input to be processed
     * @return true if the command is not "bye", false otherwise
     */
    private boolean processUserCommand(String input) {
        // case where the chatbot has been first initialised
        if (input == null) { return true; }
        // case where the input is "list" => enumerate all tasks
        if (input.equals("list")) {
            listAllTasks();
            return true;
        }
        // case where the input is "bye" => terminate early
        if (input.equals("bye")) { return false; }
        // case where the input is the mark command => mark the task as done
        if (input.startsWith("mark")) {
            Task currentTask = this.items[Integer.parseInt(input.substring(5, 6)) - 1];
            handleMarkTask(currentTask);
            echoTaskMarked(currentTask);
            return true;
        }
        // case where the input is unmark
        if (input.startsWith("unmark")) {
            Task currentTask = this.items[Integer.parseInt(input.substring(7, 8)) - 1];
            handleUnmarkTask(currentTask);
            echoTaskUnmarked(currentTask);
            return true;
        }
        Task createdTask = addTask(input);
        echoTaskAdded(createdTask);
        return true;
    }

    /**
     * When called, awaits user input. If the input is "list", the tasks
     * that the user has added to the list so far is printed to the console.
     * If the input is "bye" the program will terminate. For other inputs,
     * the input will be added and saved as a task.
     *
     * @author Tan Kerway
     */
    private void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isNotDone = true;
        while (sc.hasNextLine() && isNotDone) {
            input = sc.nextLine(); // get the input from the user
            isNotDone = processUserCommand(input); // process the input
        }
    }

    /**
     * When called, will bid the user farewell.
     *
     * @author Tan Kerway
     */
    private void sayGoodBye() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you a-nyan soon!");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Function that starts a chat with the user.
     *
     * @author Kerway
     */
    private void initiateChat() {
        greet();
        handleUserInput();
        sayGoodBye();
    }

    /**
     * This main function, when run, will cause the Chat Bot
     * to greet the user, and then exit.
     *
     * @author Tan Kerway
     * @param args pointer to some array of command-line arguments
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        dukeInstance.initiateChat();
    }
}
