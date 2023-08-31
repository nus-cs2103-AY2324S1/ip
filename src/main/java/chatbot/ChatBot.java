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
    static final String name = "4F5DA2";
    static final String localDirectoryPath = "./data";
    static final String localFilePath = localDirectoryPath + "/chatbot.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private boolean isExit = false;    // Whether the user has indicated to exit the program.
    private boolean noLocalFileAccess = false;    // Whether the data file cannot be accessed due to permission issue.

    /**
     * Constructor to instantiate a new ChatBot object.
     * @param directoryPath String path to the data file's directory
     * @param filePath String path to the data file
     */
    public ChatBot(String directoryPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directoryPath, filePath);
        try {
            this.tasks = new TaskList(storage.readData());
        } catch (LocalFileException e) {
            this.ui.showLoadingError(e);
            this.tasks = new TaskList();
            if (e instanceof FilePermissionException) {
                this.noLocalFileAccess = true;
            }
        }
    }

    private void run() {
        this.ui.greet();
        while (!isExit) {
            try {
                String command = this.ui.nextCommand();
                this.ui.showLine();
                this.handleCommand(command);
            } catch (ChatBotException e) {
                this.ui.output(e.toString());
            }
        }
    }

    private void handleCommand(String command) throws ChatBotException {
        String[] words = command.split(" ");
        String firstWord = words[0];
        switch (firstWord) {
        case "bye":
            this.isExit = true;
            this.ui.farewell();
            break;
        case "list":
            this.ui.output(this.tasks.listTasks());
            break;
        case "mark":
        case "unmark":
            boolean isDone = words[0].equals("mark");
            String taskString = this.tasks.markAs(isDone, Parser.parseMarkCommand(words));
            this.writeTaskList();
            this.ui.output(String.format("\t%s\n\t%s",
                    isDone ? "Nice! I've marked this task as done:"
                            : "OK, I've marked this task as not done yet:",
                    taskString));
            break;
        case "delete":
            taskString = this.tasks.deleteTask(Parser.parseDeleteCommand(words));
            this.writeTaskList();
            this.ui.output(String.format("\tNoted. I've removed this task:\n\t%s\n" +
                            "\tNow you have %d tasks in the list.",
                    taskString,
                    this.tasks.getSize()));
            break;
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
            this.ui.output(String.format("\tGot it. I've added this task:\n\t\t%s" +
                            "\n\tNow you have %d tasks in the list",
                    task,
                    this.tasks.getSize()
            ));
            break;
        default:
            throw new IllegalCommandException();
        }
    }

    private void writeTaskList() throws LocalFileException {
        if (!noLocalFileAccess) {
            this.storage.writeToDataFile(this.tasks.taskListToStrings());
        }
    }

    public static void main(String[] args) {
        new ChatBot(ChatBot.localDirectoryPath, ChatBot.localFilePath).run();
    }
}
