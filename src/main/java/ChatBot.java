public class ChatBot {
    static final String name = "4F5DA2";
    static final String localFilePath = "./data/chatbot.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private boolean isExit = false;

    public ChatBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.readData());
        } catch (LocalFileException e) {
            this.ui.showLoadingError(e);
            this.tasks = new TaskList();
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

    public void handleCommand(String command) throws ChatBotException {
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
            if (words.length != 2) {
                throw new MarkMissingFieldException();
            }
            try {
                boolean isDone = words[0].equals("mark");
                String taskString = this.tasks.markAs(words[0].equals("mark"), Integer.parseInt(words[1]));
                this.writeTaskList();
                this.ui.output(String.format("\t%s\n\t%s",
                        isDone ? "Nice! I've marked this task as done:"
                                : "OK, I've marked this task as not done yet:",
                        taskString));
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException();
            }
            break;
        case "delete":
            if (words.length != 2) {
                throw new DeleteMissingFieldException();
            }
            try {
                String taskString = this.tasks.deleteTask(Integer.parseInt(words[1]));
                this.writeTaskList();
                this.ui.output(String.format("\tNoted. I've removed this task:\n\t%s\n" +
                                "\tNow you have %d tasks in the list.",
                        taskString,
                        this.tasks.getSize()));
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException();
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            Task task = words[0].equals("todo")
                    ? parseTodoTaskCommand(command)
                    : words[0].equals("deadline")
                    ? parseDeadlineTaskCommand(command)
                    : parseEventTaskCommand(command);
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

    private static Task parseTodoTaskCommand(String command) throws TodoMissingFieldException{
        try {
            return new ToDoTask(command.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new TodoMissingFieldException();
        }

    }

    private static Task parseDeadlineTaskCommand(String command) throws DeadlineMissingFieldException{
        int idOfBy = command.indexOf("/by");
        if (idOfBy == -1) {
            throw new DeadlineMissingFieldException();
        }
        try {
            String name = command.substring(9, idOfBy - 1);
            String deadline = command.substring(idOfBy + 4);
            if (name.isEmpty() || deadline.isEmpty()) {
                throw new DeadlineMissingFieldException();
            }
            return new DeadlineTask(name, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new DeadlineMissingFieldException();
        }
    }

    private static Task parseEventTaskCommand(String command) throws EventMissingFieldException {
        int idOfFrom = command.indexOf("/from");
        int idOfTo = command.indexOf("/to");
        if (idOfFrom == -1 || idOfTo == -1) {
            throw new EventMissingFieldException();
        }
        try {
            String name = command.substring(6, idOfFrom - 1);
            String from = command.substring(idOfFrom + 6, idOfTo - 1);
            String to = command.substring(idOfTo + 4);
            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EventMissingFieldException();
            }
            return new EventTask(name, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw new EventMissingFieldException();
        }
    }

    public void writeTaskList() throws LocalFileException {
        this.storage.writeToDataFile(this.tasks.taskListToStrings());
    }

    public static void main(String[] args) {
        new ChatBot(ChatBot.localFilePath).run();
    }
}
