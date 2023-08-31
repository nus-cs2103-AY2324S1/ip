import java.util.ArrayList;
import java.util.Scanner;

public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String LOGO =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|";

    /**
     * A list of tasks for the chatbot.
     */
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * The filepath used to save data.
     */
    private static final String SAVE_FILE = "../data/crusader.txt";

    /**
     * Describes the list of tasks in the chatbot.
     */
    private static void tasksToString() {
        System.out.println("Here are your tasks:");
        for (int x = 0; x < TASKS.size(); x++) {
            System.out.printf("%d. %s\n", x + 1, TASKS.get(x).toString());
        }
        addDivider();
    }

    /**
     * Marks a task as done.
     *
     * @param i index of the task to be marked. 1-indexed.
     */
    private static void mark(int i) {
        Task t = TASKS.get(i - 1);
        System.out.println("I have marked the task as done:");
        t.mark();
        System.out.println(t.toString());
    }

    /**
     * Unmarks a task.
     *
     * @param i index of the task to be unmarked. 1-indexed.
     */
    private static void unmark(int i) {
        Task t = TASKS.get(i - 1);
        System.out.println("I have unmarked the task, it is no longer done:");
        t.unmark();
        System.out.println(t.toString());
    }

    /**
     * Deletes a task.
     *
     * @param i index of the task to be deleted. 1-indexed.
     */
    private static void delete(int i) {
        Task t = TASKS.get(i - 1);
        System.out.println("I have deleted the following task:");
        System.out.println(t.toString());
        TASKS.remove(t);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
    }

    /**
     * Displays the logo for the chatbot.
     */
    private static void showLogo() {
        System.out.println(LOGO);
        addDivider();
    }

    /**
     * Generates a horizontal line to divide parts of the conversation.
     */
    private static void addDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Makes the chatbot say something.
     * Also adds a divider.
     */
    private static void say(String line) {
        System.out.println(line);
        addDivider();
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        say("Hi, I am CRUSADER\nWhat can I do for you?");
    }

    /**
     * Says goodbye to the user.
     */
    private static void farewell() {
        say("Bye!\nHave a great day!");
    }

    /**
     * Parses the prompt to generate a new Todo Task.
     * @param prompt
     */
    private static void addTodo(String prompt) {
        if (prompt.length() < 5) {
            throw new IllegalArgumentException("Hmm, todo should have something inside!");
        }
        String name = prompt.substring(5);
        Todo t = new Todo(name);
        System.out.println("Adding the task:");
        System.out.println(t.toString());
        TASKS.add(t);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
        addDivider();
    }

    /**
     * Parses the prompt to generate a new Event Task.
     * @param prompt
     */
    private static void addEvent(String prompt) {
        int fromPosition = prompt.indexOf("/from");
        if (fromPosition < 0) {
            throw new IllegalArgumentException("Hmm, an event must have /from!");
        }
        int toPosition = prompt.indexOf("/to");
        if (toPosition < 0) {
            throw new IllegalArgumentException("Hmm, an event must have /to!");
        }
        if (toPosition <= fromPosition ) {
            throw new IllegalArgumentException("Hmm, /to should be in front of /from!");
        }
        if (fromPosition < 7) {
            throw new IllegalArgumentException("There should be an event name!");
        }
        if (fromPosition + 6 > toPosition - 1) {
            throw new IllegalArgumentException("Please specify a /from parameter!");
        }
        if (toPosition + 4 > prompt.trim().length()) {
            throw new IllegalArgumentException("Please specify a /to parameter!");
        }
        String name = prompt.substring(6, fromPosition - 1);
        String from = prompt.substring(fromPosition + 6, toPosition - 1);
        String to = prompt.substring(toPosition + 4);
        Event e = new Event(name, from, to);
        System.out.println("Adding the task:");
        System.out.println(e.toString());
        TASKS.add(e);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
        addDivider();
    }

    /**
     * Parses the prompt to generate a new Deadline Task.
     * @param prompt
     */
    private static void addDeadline(String prompt) {
        int byPosition = prompt.indexOf("/by");
        if (byPosition < 0) {
            throw new IllegalArgumentException("Hmm, a deadline must have /by!");
        }
        if (byPosition + 4 > prompt.trim().length()) {
            throw new IllegalArgumentException("Please specify a /to parameter!");
        }
        String name = prompt.substring(9, byPosition - 1);
        String by = prompt.substring(byPosition + 4);
        Deadline d = new Deadline(name, by);
        System.out.println("Adding the task:");
        System.out.println(d.toString());
        TASKS.add(d);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
        addDivider();
    }

    private static void loadIntoTasks(String loadPath) {
        
    }

    private static void saveTasks(String savePath) {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean notEnded = true;
        addDivider();
        showLogo();
        greet();
        loadIntoTasks(SAVE_FILE);
        while (notEnded) {
            String currentPrompt = sc.nextLine();
            addDivider();
            // Match the command based on the first word of the string
            switch (currentPrompt.contains(" ")
                    ? currentPrompt.split(" ")[0]
                    : currentPrompt) {
                case "bye":
                    notEnded = false;
                    break;
                case "list":
                    tasksToString();
                    break;
                case "mark":
                    try {
                        int i = Integer.parseInt(currentPrompt.split(" ")[1]);
                        mark(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        say("Hmm, there's supposed to be something in front of \"mark\"!");
                    } catch (NumberFormatException e) {
                        say("Hmm, there should be a NUMBER in front of \"mark\"!");
                    }
                    break;
                case "unmark":
                    try {
                        int j = Integer.parseInt(currentPrompt.split(" ")[1]);
                        unmark(j);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        say("Hmm, there's supposed to be something in front of \"unmark\"!");
                    } catch (NumberFormatException e) {
                        say("Hmm, there should be a NUMBER in front of \"unmark\"!");
                    }
                    break;
                case "todo":
                    try {
                        addTodo(currentPrompt);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        addEvent(currentPrompt);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        addDeadline(currentPrompt);
                    } catch (IllegalArgumentException e) {
                        say(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        int k = Integer.parseInt(currentPrompt.split(" ")[1]);
                        delete(k);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        say("Hmm, there's supposed to be something in front of \"delete\"!");
                    } catch (NumberFormatException e) {
                        say("Hmm, there should be a NUMBER in front of \"delete\"!");
                    }
                    break;
                default:
                    say("Sorry, but I'm not sure what that is...");
                    break;
            }
        }
        saveTasks(SAVE_FILE);
        farewell();
    }
}
