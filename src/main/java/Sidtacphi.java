import java.util.Objects;
import java.util.Scanner;

/**
 * Sidtacphi is the main class for the Sidtacphi bot.
 * 
 * @author Jeffry Lum
 * @author Damith C. Rajapakse
 * @author Sean Leong
 * @author Liow Jia Cheng
 * @author Yu Lexuan
 */
public class Sidtacphi {
    private static Task[] taskList = new Task[100];
    private static int listPtr = 0;
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    
    /**
     * The main method for the Sidtacphi class.
     * 
     * @param args
     */
    public static void main(String[] args) {
        startBot();
    }

    /**
     * This method starts the Sidtacphi bot.
     */
    private static void startBot() {
        String logo = " _______  ___   ______   _______  _______  _______  _______  __   __  ___  \n"
                + "|       ||   | |      | |       ||   _   ||       ||       ||  | |  ||   | \n"
                + "|  _____||   | |  _    ||_     _||  |_|  ||       ||    _  ||  |_|  ||   | \n"
                + "| |_____ |   | | | |   |  |   |  |       ||       ||   |_| ||       ||   | \n"
                + "|_____  ||   | | |_|   |  |   |  |       ||      _||    ___||       ||   | \n"
                + " _____| ||   | |       |  |   |  |   _   ||     |_ |   |    |   _   ||   | \n"
                + "|_______||___| |______|   |___|  |__| |__||_______||___|    |__| |__||___| \n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        readInputs();
    }

    /**
     * This method stops the Sidtacphi bot.
     */
    private static void stopBot() {
        System.out.println("\nSidtacphi: Goodbye non-Euclidean life form.");
        System.out.println("\n____________________________________________________________");
    }

    /**
     * The method for reading inputs for the bot.
     */
    private static void readInputs() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nYou: ");
        String input = "";
        while (true) { 
            input = scan.nextLine().trim();
            if (Objects.equals(input, "bye")) {
                stopBot();
                break;
            } else if (Objects.equals(input, "list")) {
                showTaskList();
            } else if (input.length() > 5 && Objects.equals(input.substring(0, 5), "mark ")) {
                try {
                    int taskId = Integer.parseInt(input.substring(5));
                    Task task = taskList[taskId - 1];
                    if (task.isCompleted()) {
                        System.out.println("\nSidtacphi: \"" + task + "\" is already marked!");
                    } else {
                        task.mark();
                        System.out.println("\nSidtacphi: Marked \"" + task + "\".");
                    }
                } catch (Exception e) {
                    System.out.println("\nSidtacphi: Invalid task ID. ");
                }
            } else if (input.length() > 7 && Objects.equals(input.substring(0, 7), "unmark ")) {
                try {
                    int taskId = Integer.parseInt(input.substring(7));
                    Task task = taskList[taskId - 1];
                    if (!task.isCompleted()) {
                        System.out.println("\nSidtacphi: \"" + task + "\" is already unmarked!");
                    } else {
                        task.unmark();
                        System.out.println("\nSidtacphi: Unmarked \"" + task + "\".");
                    }
                } catch (Exception e) {
                    System.out.println("\nSidtacphi: Invalid task ID. ");
                }
            } else if (input.length() > 5 && Objects.equals(input.substring(0, 5), "todo ")) {
                addTask(TaskType.TODO, input.substring(5));
            } else if (input.length() > 6 && Objects.equals(input.substring(0, 6), "event ")) {
                String[] inputArgs = input.substring(6).split("\\s*/from\\s*");
                if (inputArgs.length == 2) { 
                    String[] startAndEnd = inputArgs[1].split("\\s*/to\\s*");
                    if (startAndEnd.length == 2) {
                        addTask(TaskType.EVENT, new String[]{inputArgs[0], startAndEnd[0], startAndEnd[1]});
                    } else {
                        System.out.print("\nSidtacphi: Please put in the starting and ending time " 
                        + "using \"/from <time>\" followed by \"/to <time>\" for Event tasks. \n");
                    }
                } else {
                    System.out.print("\nSidtacphi: Please put in the starting and ending time " 
                    + "using \"/from <time>\" followed by \"/to <time>\" for Event tasks. \n");
                }
            } else if (input.length() > 9 && Objects.equals(input.substring(0, 9), "deadline ")) {
                String[] inputArgs = input.substring(9).split("\\s*/by\\s*");
                if (inputArgs.length == 2) { 
                    addTask(TaskType.DEADLINE, inputArgs);
                } else if (inputArgs.length == 1) {
                    System.out.print("\nSidtacphi: Please write in the deadline using \"/by <time>\" for Deadline tasks. \n");
                } else {
                    System.out.print("\nSidtacphi: Please do not write in more than 1 deadline. \n");
                }
            } else {
                System.out.print("\nSidtacphi: \"" + input + "\". is not a valid command. \n");
            }
            System.out.print("\nYou: ");
        }
        scan.close();
    }

    /**
     * Adds the input to the task_list kept track of by the bot.
     * 
     * @param input Input to add to the task_list kept by the bot.
     */
    private static void addTask(TaskType taskType, String... input) {
        // in case of > 100 messages, we will not add any more messages
        if (listPtr >= taskList.length - 1) {
            System.out.print("\nSidtacphi: You have too many tasks.\n");
            return;
        }

        switch (taskType) {
            case TODO:
                taskList[listPtr] = new Todo(input[0]);
                break;
            case EVENT:
                taskList[listPtr] = new Event(input[0], input[1], input[2]);
                break;
            case DEADLINE:
                taskList[listPtr] = new Deadline(input[0], input[1]);
                break;
        }
        
        System.out.println("\nSidtacphi: I have added \"" + taskList[listPtr] + "\".");
        listPtr++;
        if (listPtr == 1) {
            System.out.println("Sidtacphi: You now have 1 task in your list.");
        } else {
            System.out.println("Sidtacphi: You now have " + listPtr + " tasks in your list.");
        }
    }

    /**
     * Prints the task list.
     */
    private static void showTaskList() {
        if (listPtr == 0) {
            System.out.println("\nSidtacphi: There are no tasks in your list.");
            return;
        }
        System.out.println("\nSidtacphi: These are the tasks in your list.");
        for (int i = 0; i < listPtr; i++) {
            System.out.println("" + (i + 1) + ". " + taskList[i]);
        }
    }
}
