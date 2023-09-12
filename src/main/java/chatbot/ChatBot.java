package chatbot;

import chatbot.exceptions.ChatBotException;
import chatbot.exceptions.DeadlineMissingFieldException;
import chatbot.exceptions.DeleteMissingFieldException;
import chatbot.exceptions.EventMissingFieldException;
import chatbot.exceptions.FilePermissionException;
import chatbot.exceptions.FindMissingFieldException;
import chatbot.exceptions.IllegalCommandException;
import chatbot.exceptions.InvalidTaskIndexException;
import chatbot.exceptions.LocalFileException;
import chatbot.exceptions.MarkMissingFieldException;
import chatbot.exceptions.TodoMissingFieldException;
import chatbot.tasks.Task;

/**
 * Main ChatBot class which instantiates a ChatBot object that coordinates other components.
 */
public class ChatBot {
    static final String NAME = "4F5DA2";
    static final String LOCAL_DIRECTORY_PATH = "./data";
    static final String LOCAL_FILE_PATH = LOCAL_DIRECTORY_PATH + "/chatbot.txt";
    public static final String INIT_TASKLIST_SUCCESS_STRING = "Init TaskList Success";
    private Storage storage;
    private TaskList tasks;
    private boolean noLocalFileAccess = false; // Whether the data file cannot be accessed due to permission issue.

    /**
     * Constructor to instantiate a new ChatBot object.
     */
    public ChatBot() {
        this.storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
    }

    /**
     * Attempt to initiate task list from local data file.
     *
     * @return INIT_TASKLIST_SUCCESS_STRING, if the initiation is successful
     *         String representation of the exception, if any exception was thrown
     */
    public String initTaskList() {
        try {
            this.tasks = new TaskList(storage.readData());
            return INIT_TASKLIST_SUCCESS_STRING;
        } catch (LocalFileException e) {
            this.tasks = new TaskList();
            if (e instanceof FilePermissionException) {
                this.noLocalFileAccess = true;
            }
            return e.toString();
        }
    }

    /**
     * Obtain the greeting message to be displayed at the start.
     *
     * @return the String of greeting message
     */
    public String greet() {
        return "Welcome back, human!\n"
                + "I'm your personal chatBot, " + NAME + ".\n"
                + "What can I do for you today?";
    }

    /**
     * Obtain the farewell message in response to "bye" command
     *
     * @return the String of farewell message
     */
    private String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Process and execute the command from user input
     *
     * @param command the String input of user's command
     * @return the String response from the ChatBot
     * @throws ChatBotException if an error is encountered during the execution
     */
    public String handleCommand(String command) throws ChatBotException {
        assert (!command.isEmpty()); // empty command should be blocked in GUI method

        String[] words = command.split(" ");
        if (words.length == 0) {
            throw new IllegalCommandException();
        }

        String firstWord = words[0];
        switch (firstWord) {
        case "bye":
            return this.handleByeCommand();
        case "list":
            return this.handleListCommand();
        case "find":
            return this.handleFindCommand(command);
        case "mark":
        case "unmark":
            return this.handleMarkCommand(words);
        case "delete":
            return this.handleDeleteCommand(words);
        case "todo":
        case "deadline":
        case "event":
            return this.handleScheduleCommand(command, words);
        default:
            throw new IllegalCommandException();
        }
    }

    private String handleByeCommand() {
        return this.farewell();
    }

    private String handleListCommand() {
        return this.tasks.listTasks();
    }

    private String handleFindCommand(String command) throws FindMissingFieldException {
        String name = Parser.parseFindCommand(command);
        return this.tasks.findTasks(name);
    }

    private String handleMarkCommand(String[] words)
            throws MarkMissingFieldException, InvalidTaskIndexException, LocalFileException {
        boolean isDone = words[0].equals("mark");
        String taskString = this.tasks.markAs(isDone, Parser.parseMarkCommand(words));
        this.writeTaskList();
        return String.format("%s\n%s",
                isDone ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:",
                taskString);
    }

    private String handleDeleteCommand(String[] words)
            throws DeleteMissingFieldException, InvalidTaskIndexException, LocalFileException {
        String taskString = this.tasks.deleteTask(Parser.parseDeleteCommand(words));
        this.writeTaskList();
        return String.format("Noted. I've removed this task:\n%s\n"
                        + "Now you have %d tasks in the list.",
                taskString,
                this.tasks.getSize());
    }

    private String handleScheduleCommand(String command, String[] words) throws TodoMissingFieldException,
            DeadlineMissingFieldException, EventMissingFieldException, LocalFileException {
        Task task = words[0].equals("todo")
                ? Parser.parseTodoTaskCommand(command)
                : words[0].equals("deadline")
                ? Parser.parseDeadlineTaskCommand(command)
                : Parser.parseEventTaskCommand(command);
        this.tasks.addTask(task);
        this.writeTaskList();
        return String.format("Got it. I've added this task:\n%s"
                        + "\nNow you have %d tasks in the list",
                task,
                this.tasks.getSize()
        );
    }

    private void writeTaskList() throws LocalFileException {
        if (!noLocalFileAccess) {
            this.storage.writeToDataFile(this.tasks.taskListToStrings());
        }
    }
}
