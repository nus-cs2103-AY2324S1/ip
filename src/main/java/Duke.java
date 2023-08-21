import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Duke {
    static final String name = "Atlas";
    static int taskCount = 0;
    static final int maxTaskCount = 100;
    static Task[] taskList = new Task[maxTaskCount];


    public static void main(String[] args) {
        greet();
        listen();
    }

    /**
     * Prints greeting to console.
     */
    protected static void greet() {
        String logo =
                "        _______ _                _____ \n" +
                        "     /\\|__   __| |        /\\    / ____|\n" +
                        "    /  \\  | |  | |       /  \\  | (___  \n" +
                        "   / /\\ \\ | |  | |      / /\\ \\  \\___ \\ \n" +
                        "  / ____ \\| |  | |____ / ____ \\ ____) |\n" +
                        " /_/    \\_\\_|  |______/_/    \\_\\_____/ \n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.printf("Hello, I'm %s!\n", Duke.name);
        System.out.println("What would you like me to do today?");
        printHorizontalLine();
    }

    /**
     * Prints goodbye to console.
     */
    protected static void exit() {
        System.out.println("Goodbye!");
    }

    /**
     * Listens and executes commands
     */
    protected static void listen() {
        String input, command, args;
        final String listCommand = "list";
        final String exitCommand = "bye";
        final String markItemCommand = "mark";
        final String unmarkItemCommand = "unmark";
        boolean exitChatbot = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!exitChatbot) {
            try {
                input = reader.readLine();
                printHorizontalLine();
                String[] splitInput = input.split(" ", 2);
                command = splitInput[0];
                args = splitInput.length > 1 ? splitInput[1] : "";
                switch (command) {
                    case listCommand:
                        printList();
                        break;
                    case exitCommand:
                        exitChatbot = true;
                        break;
                    case markItemCommand:
                        markItem(args);
                        break;
                    case unmarkItemCommand:
                        unmarkItem(args);
                        break;
                    default:
                        addItem(input);
                }
            } catch (IOException e) {
                System.out.println("Unable to read command, exiting");
                printHorizontalLine();
                exitChatbot = true;
            } finally {
                if (!exitChatbot) {
                    printHorizontalLine();
                }
            }
        }
        exit();
        printHorizontalLine();
    }

    /**
     * Adds task to list. If the list is already full, an error message is
     * instead printed and returned. If not, the item is added to the list.
     * @param item Item to add to list
     */
    protected static void addItem(String item) {
        if (taskCount == maxTaskCount) {
            System.out.println("Unable to add task due to exceeding " +
                    "max task count");
            return;
        }
        Task newTask = new Task(item);
        taskList[taskCount++] = newTask;
        System.out.printf("added: %s\n", item);
    }

    /**
     * Prints list in the order in which items are added.
     */
    protected static void printList() {
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf("%d.%s", i + 1, taskList[i]);
        }
    }

    /**
     * Marks task as done
     * @param idx 1-based task index
     */
    protected static void markItem(String idx) {
        try {
            Task selectedTask = getTaskByIndex(idx);
            selectedTask.checkTask();
            System.out.printf("Nice! I've marked this task as done:\n%s", selectedTask);
        } catch (NumberFormatException e) {
            System.out.println("Task index must be an integer");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index out of bounds, no action taken");
        }
    }

    /**
     * Marks task as not done
     * @param idx 1-based task index
     */
    protected static void unmarkItem(String idx) {
        try {
            Task selectedTask = getTaskByIndex(idx);
            selectedTask.uncheckTask();
            System.out.printf("OK, I've marked this task as not done yet:\n%s", selectedTask);
        } catch (NumberFormatException e) {
            System.out.println("Task index must be an integer");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index out of bounds, no action taken");
        }
    }

    /**
     * Prints a horizontal line containing the character '-' of width 80.
     */
    protected static void printHorizontalLine() {
        final int consoleWidth = 80;
        String line = "_".repeat(consoleWidth);
        System.out.println(line);
    }

    /**
     * Returns task from task list using 1-based index
     * @param args String containing task index (1-based index)
     * @return Task at selected index (e.g. if args is "2", returns 2nd task in list)
     * @throws IndexOutOfBoundsException Task index does not refer to a valid task
     */
    protected static Task getTaskByIndex(String args) throws IndexOutOfBoundsException {
        int idx = Integer.parseUnsignedInt(args, 10);
        if (idx <= 0 || idx > taskCount) {
            throw new IndexOutOfBoundsException();
        }
        return taskList[idx - 1];
    }
}
