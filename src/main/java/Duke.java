import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static String HORIZONTAL_LINE = "    __________________________________________________"; //50 underscores.
    static String INDENT = "    "; //4 spaces.
    static ArrayList<Task> taskList = new ArrayList<>(100);

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

    /**
     * Function to mark a given task as done.
     * @param taskIndex the index of the task to be marked as done.
     */
    public static void markTask(int taskIndex) {
        System.out.println(HORIZONTAL_LINE);
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            System.out.printf("     Invalid Index of Task. You currently have Task 1 to Task %d\n", taskList.size());
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
            System.out.printf("    Invalid Index of Task. You currently have Task 1 to Task %d\n", taskList.size());
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

    public static void main(String[] args) {
        greet();
        try (Scanner scanner = new Scanner(System.in)) {
            Boolean repeatFlag = true;
            while (repeatFlag) {
                String userInput = scanner.nextLine();
                //Level-4 Inrement: Use userInput.startWith() to check first word before splitting

                String[] words = userInput.split("\\s+"); // Split input by space, put into array
                String formattedInput = userInput.toLowerCase();
                if (formattedInput.equals("bye")) {
                    farewell();
                    repeatFlag = false;
                } else if (userInput.equals("list")) {
                    listAllTasks();
                } else if (words[0].equals("mark")) {
                    int taskIndex = Integer.parseInt(words[1]) - 1; // Potential Error if next input is can't be converted to Integer
                    markTask(taskIndex);
                } else if (words[0].equals("unmark")) {
                    int taskIndex = Integer.parseInt(words[1]) - 1; // Potential Error if next input is can't be converted to Integer
                    unmarkTask(taskIndex);
                } else if (userInput.startsWith("deadline")){
                    Deadline.handleDeadlineTask(userInput);
                } else if (userInput.startsWith("todo")) {
                    Todo.handleTodoTask(userInput);
                } else {
                    addTask(userInput);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid command! Please enter valid commands");
            System.out.println(HORIZONTAL_LINE);
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
