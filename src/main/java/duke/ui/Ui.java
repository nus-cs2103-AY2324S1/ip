package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * This class handles the User Interface part of the chatbot,
 * managing user inputs and displaying outputs.
 */
public class Ui {
    /** For spacing purposes */
    private static final String SPACE = "------------------------------------";

    /** Scanner for user input */
    private Scanner scanner;


    /** Name of bot */
    private String name = "Adam's Bot";

    /**
     * Constructs a Ui object which initializes a Scanner object to read user inputs.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        // remove trailing spaces and get use input
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public String showError(String errorMessage) {
        System.out.println(SPACE);
        System.out.println(errorMessage);
        System.out.println(SPACE);
        return errorMessage;
    }

    /**
     * Displays a welcome message to the user when they first run the program.
     */
    public String showWelcome() {
        System.out.println(SPACE);
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        System.out.println(SPACE);
        return "Hello! I'm " + this.name + "\nWhat can I do for you?";
    }

    /**
     * Displays a goodbye message to the user when they exit the program with the command "bye".
     */
    public String showGoodbye() {
        System.out.println(SPACE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPACE);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a list of tasks to the user when they enter the command "list".
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String showTaskList(TaskList taskList) {
        String result = "";
        System.out.println(SPACE);

        // iterate through ArrayList to print tasks
        for (int i = 0; i < taskList.size(); i++) {
            int currentNumber = i + 1;
            System.out.println(currentNumber + ". " + taskList.get(currentNumber).toString());
            result = result + currentNumber + ". " + taskList.get(currentNumber).toString() + "\n";
        }
        System.out.println(SPACE);
        return result;
    }

    /**
     * Displays the task to the user that was marked done.
     *
     * @param task The task to be displayed after being marked done.
     */
    public String showMarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
        return "Nice! I've marked this task as done:\n" + "  " + task.toString();
    }

    /**
     * Displays the task to the user that was marked undone.
     *
     * @param task The task to be displayed after being marked undone.
     */
    public String showUnmarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
        return "Nice! I've marked this task as undone:\n" + "  " + task.toString();
    }

    /**
     * Displays the task that has been added and the size of the list after that addition.
     *
     * @param task The task to be added into the list.
     * @param size The current size of the task list after adding the task into the list.
     */
    public String showAddText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
        return "Got it. I've added this task:\n" + "  " + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays the task that has been deleted and the size of the list after that deletion.
     *
     * @param task The task to be deleted from the list.
     * @param size The current size of the task list after deleting the task from the list.
     */
    public String showDeleteText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
        return "Noted. I've removed this task:\n" + "  " + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays the tasks within the taskList that contains the key word.
     * If there isn't any task that contains the key word, display an error message.
     *
     * @param tasks The task list we are finding the key word from.
     * @param keyWord The key word that we are searching for in the task list.
     */
    public String showFindText(TaskList tasks, String keyWord) {
        boolean isKeyWordFound = false;
        int counter = 1;
        String result = "";
        System.out.println(SPACE);

        // iterate through taskList to check if they contain the keyWord. If yes, print them.
        for (int i = 0; i < tasks.size(); i++) {
            int currentNumber = i + 1;
            Task task = tasks.get(currentNumber);
            assert task != null; // Check task is not null.
            boolean hasKeyWord = task.findKeyWord(keyWord);

            if (hasKeyWord) {
                isKeyWordFound = true;
                System.out.println(counter + ". " + task.toString());
                result = result + counter + ". " + task.toString() + "\n";
                counter = counter + 1;
            }
        }

        if (!isKeyWordFound) {
            System.out.println("☹ OOPS!!! The key word you are finding for is not present.");
            return "☹ OOPS!!! The key word you are finding for is not present.";
        }
        System.out.println(SPACE);
        return result;
    }

    /**
     * Displays the updated task to the user after a modification has been made.
     * This method prints out the updated task from the task list by its task number.
     *
     * @param tasks      The TaskList object containing all tasks.
     * @param taskNumber The number of the task that has been updated.
     * @return A string summarizing the update action and displaying the updated task.
     */
    public String showUpdateText(TaskList tasks, int taskNumber) {
        System.out.println(SPACE);
        System.out.println("Noted. I've updated task number" + taskNumber + ".\n");
        System.out.println(tasks.get(taskNumber).toString());
        System.out.println(SPACE);
        return "Noted. I've updated this task.\n" + tasks.get(taskNumber).toString();
    }
}

