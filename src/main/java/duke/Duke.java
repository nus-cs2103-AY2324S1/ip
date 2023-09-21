package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.task.Task;
import javafx.util.Pair;

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

            assert this.tasks.getSize() != 0 : "taskList initialized from file cannot be empty";
            
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
            String botOutput = generateResponse(userInput);

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
     * @return A response generated based on the user input.
     */
    public String generateResponse(String userInput) {
        // Initialize the bot's response.
        String botOutput = "";

        // Check for various user commands and generate responses accordingly.
        if (userInput.equalsIgnoreCase(Command.START)) {
            botOutput = botOutput + this.ui.getEntryGreeting();
        } else if (userInput.strip().equalsIgnoreCase(Command.LIST)) {
            // Generate a list of tasks and display it to the user.
            botOutput = botOutput + "Here are the tasks in your list: \n    " + this.tasks.toString();

        } else if (userInput.strip().equalsIgnoreCase(Command.LIST_WITHIN_WEEK)) {
            // Generate a list of tasks due within a week and display it to the user.
            TaskList listWeek = this.tasks.dueWithinWeek();
            botOutput = botOutput + "Here are the tasks in your list that start/due within one week: \n    "
                    + listWeek.toString();

        } else if (userInput.strip().equalsIgnoreCase(Command.LIST_WITHIN_MONTH)) {
            // Generate a list of tasks due within a month and display it to the user.
            TaskList monthWeek = this.tasks.dueWithinMonth();
            botOutput = botOutput + "Here are the tasks in your list that start/due within one month: \n    "
                    + monthWeek.toString();

        } else if (userInput.strip().startsWith(Command.MARK)) {
            botOutput = markResponse(userInput.strip(), botOutput);
        } else if (userInput.strip().startsWith(Command.UNMARK)) {
            botOutput = unmarkResponse(userInput.strip(), botOutput);
        } else if (userInput.strip().startsWith(Command.DELETE)) {
            botOutput = deleteResponse(userInput.strip(), botOutput);
        } else if (userInput.strip().startsWith(Command.TAG)) {
            botOutput = tagResponse(userInput.strip(), botOutput);
        } else if (userInput.strip().startsWith(Command.DOAFTER)) {
            botOutput = doafterResponse(userInput.strip(), botOutput);
        } else if (userInput.strip().startsWith(Command.FIND)) {
            botOutput = findResponse(userInput.strip(), botOutput);
        } else if (userInput.strip().equalsIgnoreCase(Command.EXIT)) {
            botOutput = this.ui.getExitGreeting();
        } else if (userInput.strip().equalsIgnoreCase(Command.MOTIVATE)) {
            botOutput = "Here's your dose of motivation, Sergeant!";
        } else {
            botOutput = taskCreationResponse(userInput.strip(), botOutput);
        }

        assert botOutput.isEmpty() == false : "botOutput message cannot be empty";

        botOutput = botOutput + this.ui.getQuote();

        try {
            // Write the updated taskList into file
            this.storage.saveTasksToFile(this.tasks);

        } catch (IOException | InvalidFileFormatException e) {
            // Handle exceptions related to file loading.
            System.out.println(e.getMessage());
            botOutput += "\n\n Task file was not written successfully.";
        }

        // Return the generated bot response to run() method.
        return botOutput;
    }

    private String taskCreationResponse(String userInput, String botOutput) {
        try {
            // Attempt to create a new task based on the user input.
            Task t = Task.taskCon(userInput);

            assert t != null : "created task t cannot be null";

            this.tasks.addTask(t);
                botOutput = botOutput + "added: " + t + "\n    Now you have " + this.tasks.getSize() + " tasks in the list.";
        } catch (InvalidCommandException e) {
            // Handle invalid commands.
            botOutput = "OOPS!!! I'm sorry, but I'm afraid I don't comprehend Major!";
        } catch (InvalidTaskCreationException t) {
            // Handle invalid task creation.
            botOutput = t.getMessage();
        } catch (DateTimeParseException d) {
            // Handle date and time format exceptions.
            botOutput = "Please specify deadlines and dates in the following format, " + Task.DATE_TIME_FORMAT;
        }
        return botOutput;
    }

    private String findResponse(String userInput, String botOutput) {
        try {
            String[] queryString = parser.parseFind(userInput, this.tasks);
            TaskList listSearchMatches = this.tasks.searchMatches(queryString);
            botOutput = botOutput + "Here are the matching tasks in your list: \n    "
                    + listSearchMatches.toString();
        } catch (ParserException p) {
            botOutput = p.getMessage();
        }
        return botOutput;
    }

    private String doafterResponse(String userInput, String botOutput) {
        botOutput = botOutput + "Noted. I've added dependency to this task: \n    ";
        try {
            Pair<Integer, Integer> p = parser.parseDoAfter(userInput, this.tasks);
            int childTaskNo = p.getKey();
            int parentTaskNo = p.getValue();
            Task child = this.tasks.getTask(childTaskNo - 1);
            Task parent = this.tasks.getTask(parentTaskNo - 1);
            child.setParentTask(parent);
            assert child != null : "retrieved task child cannot be null";
            assert parent != null : "retrieved task parent cannot be null";

            botOutput += child;
        } catch (ParserException p) {
            // Handle parsing exceptions.
            botOutput = p.getMessage();
        }
        return botOutput;
    }

    private String tagResponse(String userInput, String botOutput) {
        botOutput = botOutput + "Noted. I've added tags to this task: \n    ";
        try {
            Pair<Integer, String[]> p = parser.parseTag(userInput, this.tasks);
            int taskNo = p.getKey();
            String[] tags = p.getValue();
            Task x = this.tasks.getTask(taskNo - 1);
            if (tags.length == 0) {
                throw new ParserException("Please enter valid tags in the format: 'tag 4 fun sport'");
            }
            x.addTags(tags);
            assert x != null : "retrieved task x cannot be null";

            botOutput += x;
        } catch (ParserException p) {
            // Handle parsing exceptions.
            botOutput = p.getMessage();
        }
        return botOutput;
    }

    private String deleteResponse(String userInput, String botOutput) {
        // Process a command to delete a task and display the deleted task.
        botOutput = botOutput + "Noted. I've removed this task: \n    ";
        try {
            int taskNo = parser.parseDelete(userInput, this.tasks);
            Task x = this.tasks.deleteTask(taskNo - 1);

            assert x != null : "retrieved task x cannot be null";

            botOutput += x;
        } catch (ParserException p) {
            // Handle parsing exceptions.
            botOutput = p.getMessage();
        }
        return botOutput;
    }

    private String unmarkResponse(String userInput, String botOutput) {
        // Process a command to mark a task as not done and display the result.
        botOutput = botOutput + "Ok, I've marked this task as not done yet: \n    ";
        try {
            int taskNo = parser.parseUnmark(userInput, this.tasks);
            Task x = this.tasks.getTask(taskNo - 1);

            assert x != null : "retrieved task x cannot be null";

            x.markAsUndone();
            botOutput += x;
        } catch (ParserException p) {
            // Handle parsing exceptions.
            botOutput = p.getMessage();
        }
        return botOutput;
    }

    private String markResponse(String userInput, String botOutput) {
        // Process a command to mark a task as done and display the result.
        botOutput = botOutput + "Nice! I've marked this task as done: \n    ";
        try {
            int taskNo = parser.parseMark(userInput, this.tasks);
            Task x = this.tasks.getTask(taskNo - 1);

            assert x != null : "retrieved task x cannot be null";

            x.markAsDone();
            botOutput += x;
        } catch (ParserException p) {
            // Handle parsing exceptions.
            botOutput = p.getMessage();
        }
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
//        assert false: "falseee";
        // Create an instance of Duke and run the bot.
        Duke dukeInstance = new Duke(filePath);
        dukeInstance.runBot();
    }
}
