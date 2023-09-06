package chatbot;

import chatbot.exceptions.ChatBotException;
import chatbot.exceptions.FilePermissionException;
import chatbot.exceptions.IllegalCommandException;
import chatbot.exceptions.LocalFileException;
import chatbot.tasks.Task;

/**
 * Main ChatBot class which instantiates a ChatBot object that coordinates other components.
 */
public class ChatBot {
    static final String NAME = "4F5DA2";
    static final String LOCAL_DIRECTORY_PATH = "./data";
    static final String LOCAL_FILE_PATH = LOCAL_DIRECTORY_PATH + "/chatbot.txt";
    private final Storage storage;
    private TaskList tasks;
    private boolean isExit = false; // Whether the user has indicated to exit the program.
    private boolean noLocalFileAccess = false; // Whether the data file cannot be accessed due to permission issue.

    /**
     * Constructor to instantiate a new ChatBot object.
     */
    public ChatBot() {
        this.storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
    }

    public String initTaskList() {
        try {
            this.tasks = new TaskList(storage.readData());
            return "ok";
        } catch (LocalFileException e) {
            this.tasks = new TaskList();
            if (e instanceof FilePermissionException) {
                this.noLocalFileAccess = true;
            }
            return e.toString();
        }
    }

    public String greet() {
        return "Welcome back, human!\n"
                + "I'm your personal chatBot, " + NAME + ".\n"
                + "What can I do for you today?";
    }

    private String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    public String handleCommand(String command) throws ChatBotException {
        String[] words = command.split(" ");
        String firstWord = words[0];
        switch (firstWord) {
        case "bye":
            this.isExit = true;
            return this.farewell();
        case "list":
            return this.tasks.listTasks();
        case "find":
            String name = Parser.parseFindCommand(command);
            return this.tasks.findTasks(name);
        case "mark":
        case "unmark":
            boolean isDone = words[0].equals("mark");
            String taskString = this.tasks.markAs(isDone, Parser.parseMarkCommand(words));
            this.writeTaskList();
            return String.format("%s\n%s",
                    isDone ? "Nice! I've marked this task as done:"
                            : "OK, I've marked this task as not done yet:",
                    taskString);
        case "delete":
            taskString = this.tasks.deleteTask(Parser.parseDeleteCommand(words));
            this.writeTaskList();
            return String.format("Noted. I've removed this task:\n%s\n"
                            + "Now you have %d tasks in the list.",
                    taskString,
                    this.tasks.getSize());
        case "todo":
        case "deadline":
        case "event":
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
        default:
            throw new IllegalCommandException();
        }
    }

    private void writeTaskList() throws LocalFileException {
        if (!noLocalFileAccess) {
            this.storage.writeToDataFile(this.tasks.taskListToStrings());
        }
    }
}
