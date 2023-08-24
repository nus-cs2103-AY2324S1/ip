import java.util.ArrayList;
import java.util.Scanner;

public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String logo =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|";

    /**
     * A list of tasks for the chatbot.
     */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Describes the list of tasks in the chatbot.
     */
    private static void tasksToString() {
        System.out.println("Here are your tasks:");
        for (int x = 0; x < tasks.size(); x++) {
            System.out.printf("%d. %s\n", x + 1, tasks.get(x).toString());
        }
        addDivider();
    }

    /**
     * Marks a task as done.
     *
     * @param i index of the task to be marked. 1-indexed.
     */
    private static void mark(int i) {
        Task t = tasks.get(i - 1);
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
        Task t = tasks.get(i - 1);
        System.out.println("I have unmarked the task, it is no longer done:");
        t.unmark();
        System.out.println(t.toString());
    }

    /**
     * Displays the logo for the chatbot.
     */
    private static void showLogo() {
        System.out.println(logo);
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
        String name = prompt.substring(5);
        Todo t = new Todo(name);
        System.out.println("Adding the task:");
        System.out.println(t.toString());
        tasks.add(t);
        System.out.printf("Now there are %d tasks in the list.\n", tasks.size());
        addDivider();
    }

    /**
     * Parses the prompt to generate a new Event Task.
     * @param prompt
     */
    private static void addEvent(String prompt) {
        int fromPosition = prompt.indexOf("/from");
        int toPosition = prompt.indexOf("/to");
        String name = prompt.substring(6, fromPosition - 1);
        String from = prompt.substring(fromPosition + 6, toPosition - 1);
        String to = prompt.substring(toPosition + 4);
        Event e = new Event(name, from, to);
        System.out.println("Adding the task:");
        System.out.println(e.toString());
        tasks.add(e);
        System.out.printf("Now there are %d tasks in the list.\n", tasks.size());
        addDivider();
    }

    /**
     * Parses the prompt to generate a new Deadline Task.
     * @param prompt
     */
    private static void addDeadline(String prompt) {
        int byPosition = prompt.indexOf("/by");
        String name = prompt.substring(9, byPosition - 1);
        String by = prompt.substring(byPosition + 4);
        Deadline d = new Deadline(name, by);
        System.out.println("Adding the task:");
        System.out.println(d.toString());
        tasks.add(d);
        System.out.printf("Now there are %d tasks in the list.\n", tasks.size());
        addDivider();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean notEnded = true;
        addDivider();
        showLogo();
        greet();
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
                    int i = Integer.parseInt(currentPrompt.split(" ")[1]);
                    mark(i);
                    break;
                case "unmark":
                    int j = Integer.parseInt(currentPrompt.split(" ")[1]);
                    unmark(j);
                    break;
                case "todo":
                    addTodo(currentPrompt);
                    break;
                case "event":
                    addEvent(currentPrompt);
                    break;
                case "deadline":
                    addDeadline(currentPrompt);
                    break;
                default:
                    say("added: " + currentPrompt);
                    tasks.add(new Task(currentPrompt));
                    break;
            }
        }
        farewell();
    }
}
