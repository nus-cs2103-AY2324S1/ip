package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.task.Task;

/**
 * Duke is a task management bot that helps you keep track of your tasks.
 * You can add, mark as done, delete, and list tasks with Duke.
 */
public class Duke {


    private LocalDate currentDate;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser = new Parser();

    /**
     * Constructor for Duke. currentDate, ui, tasks is initialized by default.
     *
     * @param filePath The file path to the data file where tasks are stored.
     */
    public Duke(String filePath) {
        // Initialize the current date to the current system date.
        this.currentDate = LocalDate.now();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            // Attempt to load tasks from the specified file path.
            this.tasks = new TaskList(storage.load());
        } catch (IOException | InvalidFileFormatException e) {
            // Handle exceptions related to file loading.
            System.out.println(e.getMessage());
            this.tasks = new TaskList();

            System.out.println("Starting Duke with Task List...");
        }
    }

    /**
     * Sets the current date for Duke. Useful for list-within-week and list-within-month commands
     *
     * @param date The LocalDate to set as the current date.
     */
    private void setcurrentDate(LocalDate date) {
        this.currentDate = date;
    }

    /**
     * Runs the Duke bot, accepting and processing user commands. Scanner object and infinite while loop handled here.
     */
    public void runBot() {
        // Display a greeting message to the user.
        this.ui.displayGreeting();

        // Initialize a new scanner object to interact with user input from Command Line.
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase(Command.EXIT)) {
            // Generate a response based on the user's input.
            String botOutput = generateResponse(userInput, scanner, this.tasks);

            // Display the bot's response to the user.
            this.ui.displayMessage(botOutput);

            // Read the next user input.
            userInput = scanner.nextLine();
        }
        // Display an exit greeting when the bot exits.
        this.ui.displayExitGreeting();
    }

    /**
     * Generates a response for the given user input. Keeps track of and modifies TaskList list.
     *
     * @param userInput The user's input command.
     * @param scanner   The Scanner object for user input.
     * @param list      The task list to operate on.
     * @return A response generated based on the user input.
     */
    public String generateResponse(String userInput, Scanner scanner, TaskList list) {
        // Initialize the bot's response.
        String botOutput = "";

        // Check for various user commands and generate responses accordingly.
        if (userInput.equalsIgnoreCase(Command.LIST)) {
            // Generate a list of tasks and display it to the user.
            botOutput = botOutput + "Here are the tasks in your list: \n    " + list.toString();

        } else if (userInput.equalsIgnoreCase(Command.LIST_WITHIN_WEEK)) {
            // Generate a list of tasks due within a week and display it to the user.
            TaskList listWeek = list.dueWithinWeek();
            botOutput = botOutput + "Here are the tasks in your list that start/due within one week: \n    "
                    + listWeek.toString();

        } else if (userInput.equalsIgnoreCase(Command.LIST_WITHIN_MONTH)) {
            // Generate a list of tasks due within a month and display it to the user.
            TaskList monthWeek = list.dueWithinMonth();
            botOutput = botOutput + "Here are the tasks in your list that start/due within one month: \n    "
                    + monthWeek.toString();

        } else if (userInput.startsWith(Command.MARK)) {
            // Process a command to mark a task as done and display the result.
            botOutput = botOutput + "Nice! I've marked this task as done: \n    ";
            try {
                int taskNo = parser.parseMark(userInput, list);
                Task x = list.getTask(taskNo - 1);
                x.markAsDone();
                botOutput += x;
            } catch (ParserException p) {
                // Handle parsing exceptions.
                botOutput = p.getMessage();
            }
        } else if (userInput.startsWith(Command.UNMARK)) {
            // Process a command to mark a task as not done and display the result.
            botOutput = botOutput + "Ok, I've marked this task as not done yet: \n    ";
            try {
                int taskNo = parser.parseUnmark(userInput, list);
                Task x = list.getTask(taskNo - 1);
                x.markAsUndone();
                botOutput += x;
            } catch (ParserException p) {
                // Handle parsing exceptions.
                botOutput = p.getMessage();
            }
        } else if (userInput.startsWith(Command.DELETE)) {
            // Process a command to delete a task and display the deleted task.
            botOutput = botOutput + "Noted. I've removed this task: \n    ";
            try {
                int taskNo = parser.parseDelete(userInput, list);
                Task x = list.deleteTask(taskNo - 1);
                botOutput += x;
            } catch (ParserException p) {
                // Handle parsing exceptions.
                botOutput = p.getMessage();
            }
        } else if (userInput.startsWith(Command.FIND)) {
            try {
                String queryString = parser.parseFind(userInput, list);
                TaskList listSearchMatches = list.searchMatches(queryString);
                botOutput = botOutput + "Here are the matching tasks in your list: \n    "
                        + listSearchMatches.toString();
            } catch (ParserException p) {
                botOutput = p.getMessage();
            }
        } else {
            try {
                // Attempt to create a new task based on the user input.
                Task t = Task.taskCon(userInput);
                list.addTask(t);
                botOutput = botOutput + "added: " + t + "\n    Now you have " + list.getSize() + " tasks in the list.";
            } catch (InvalidCommandException e) {
                // Handle invalid commands.
                botOutput = "OOPS!!! I'm sorry, but I'm afraid I don't comprehend Sergeant!";
            } catch (InvalidTaskCreationException t) {
                // Handle invalid task creation.
                botOutput = t.getMessage();
            } catch (DateTimeParseException d) {
                // Handle date and time format exceptions.
                botOutput = "Please specify deadlines and dates in the following format, " + Task.DATE_TIME_FORMAT;
            }
        }
        // Return the generated bot response to run() method.
        return botOutput;
    }

    /**
     * The main method to start the Duke bot.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Specify the file path for data storage.
        String filePath = "./data/duke.txt";

        // Create an instance of Duke and run the bot.
        Duke dukeInstance = new Duke(filePath);
        dukeInstance.runBot();
    }
}
