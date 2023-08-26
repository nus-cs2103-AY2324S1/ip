import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    static String INDENT = "    "; //4 spaces.
    static ArrayList<Task> taskList = new ArrayList<>(100);
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, DEADLINE, TODO, EVENT, UNKNOWN
    }

    /**
     * Function to greet the User.
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Hello! I'm SeeWhyAre Bot!");
        System.out.println("    What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to say goodbye to the User and end the program.
     */
    public static void farewell() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    You are closing the SeeWhyAre chat bot.");
        System.out.println("    Bye bye. Please use me again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to add any given input into our taskList.
     */
    public static void addTask(String description) {
        System.out.println(HORIZONTAL_LINE);
        Task task = new Task(description);
        taskList.add(task);
        System.out.printf("    added: %s%n", description);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void deleteTask(String deleteInput) throws EmptyDescriptionException {
        String[] words = deleteInput.split("\\s+"); // Split input by space, put into array
        //Check for valid length
        if (words.length <= 1) {
            throw new EmptyDescriptionException("Please provide the task index to be deleted.");
        }

        //Try parsing into integer to get deleteIndex
        try {
            int deleteIndex = Integer.parseInt(words[1]) - 1; // Potential Error cannot parse to integer
            System.out.println(HORIZONTAL_LINE);

            if (deleteIndex >= 0 && deleteIndex < taskList.size()) {
                Task removedTask = taskList.remove(deleteIndex); //Actual task can be todo, deadline, or event
                System.out.println("     Noted. I've removed this task:");
                System.out.printf("       %s\n", removedTask.toString());
                System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
            } else {
                System.out.println("     ☹ OOPS!!! The task index is invalid.\n");
                System.out.printf("    You currently have %d Task(s).\n", taskList.size());
            }
            System.out.println(HORIZONTAL_LINE);

        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("     ☹ OOPS!!! Please enter the index after 'delete' command.");
            System.out.println("     For example: delete 5");
            System.out.println("     This will remove Task 5 from your Task List, assuming you have at least 5 tasks.");
            System.out.println(HORIZONTAL_LINE);
        }
    }

    /**
     * Function to mark a given task as done.
     * @param taskIndex the index of the task to be marked as done.
     */
    public static void markTask(int taskIndex) {
        System.out.println(HORIZONTAL_LINE);
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            System.out.printf("     Invalid Index of Task. You currently have %d Task(s)\n", taskList.size());
        } else {
            Task task = taskList.get(taskIndex);
            task.markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.description);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to mark a given task as done.
     * @param taskIndex the index of the task to be marked as not done yet.
     */
    public static void unmarkTask(int taskIndex) {
        System.out.println(HORIZONTAL_LINE);
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            System.out.printf("    Invalid Index of Task. You currently have %d Task(s)\n", taskList.size());
        } else {
            Task task = taskList.get(taskIndex);
            task.markAsNotDone();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.description);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to list out all tasks.
     */
    public static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    No tasks for now!");
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, taskList.get(i).toString());
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void main(String[] args) throws InvalidCommandException {
        Boolean repeatFlag = true;
        greet();
        Scanner scanner = new Scanner(System.in);

        while (repeatFlag) {
            try {
                String userInput = scanner.nextLine();
                //Level-4 Inrement: Use userInput.startWith() to check first word before splitting

                String[] words = userInput.split("\\s+"); // Split input by space, put into array
                String formattedInput = userInput.toLowerCase();
                String firstWord = words[0].toUpperCase();

                Command command; //Use enum
                try {
                    command = Command.valueOf(firstWord);
                } catch (IllegalArgumentException e) {
                    command = Command.UNKNOWN;
                }

                //A-Enum: Use switch-case instead of if-else for neater code
                switch (command) {
                case BYE:
                    farewell();
                    repeatFlag = false;
                    break;
                case LIST:
                    listAllTasks();
                    break;
                case MARK:
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    markTask(taskIndex);
                    break;
                case UNMARK:
                    taskIndex = Integer.parseInt(words[1]) - 1; //Same variable name taskIndex as above
                    unmarkTask(taskIndex);
                    break;
                case DELETE:
                    deleteTask(userInput);
                    break;
                case DEADLINE:
                    Deadline.handleDeadlineTask(userInput);
                    break;
                case TODO:
                    Todo.handleTodoTask(userInput);
                    break;
                case EVENT:
                    Event.handleEventTask(userInput);
                    break;
                case UNKNOWN:
                    throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (EmptyDescriptionException e) {
                e.printExceptionMessage();
            } catch (InvalidCommandException e) {
                e.printExceptionMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("     Please enter valid Integer index!");
                System.out.printf("     You currently have %d tasks", taskList.size());
            } catch (Exception e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("     Very Invalid command! Please enter valid commands");
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}

/**
 * String logo = " ____        _        \n"
 *                 + "|  _ \\ _   _| | _____ \n"
 *                 + "| | | | | | | |/ / _ \\\n"
 *                 + "| |_| | |_| |   <  __/\n"
 *                 + "|____/ \\__,_|_|\\_\\___|\n";
 *         System.out.println("Hello from\n" + logo);
 */
