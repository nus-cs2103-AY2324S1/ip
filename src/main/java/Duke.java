import java.util.Scanner;

public class Duke {

    private static final String NAME_ART =
            "               _                _               _                      \n" +
            "           _  /\\ \\            / /\\          _  /\\ \\               \n" +
            "          /\\_\\\\ \\ \\          / /  \\        /\\_\\\\ \\ \\        \n" +
            "         / / / \\ \\ \\        / / /\\ \\      / / / \\ \\ \\          \n" +
            "        / / /   \\ \\ \\      / / /\\ \\ \\    / / /   \\ \\ \\        \n" +
            "        \\ \\ \\____\\ \\ \\    /_/ /  \\ \\ \\   \\ \\ \\____\\ \\ \\ \n" +
            "         \\ \\________\\ \\   \\ \\ \\   \\ \\ \\   \\ \\________\\ \\ \n" +
            "          \\/________/\\ \\   \\ \\ \\   \\ \\ \\   \\/________/\\ \\  \n" +
            "                    \\ \\ \\   \\ \\ \\___\\ \\ \\            \\ \\ \\ \n" +
            "                     \\ \\_\\   \\ \\/____\\ \\ \\            \\ \\_\\ \n" +
            "                      \\/_/    \\_________\\/             \\/_/";
    private static final String NAME = "404";
    public static final String INDENT = "     ";
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        Duke robot404 = new Duke("./data", "duke.txt");
        robot404.start();
    }

    public Duke(String foldPath, String fileName) {
        this.storage = new Storage(foldPath, fileName);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            printLine();
            System.out.println(e.getMessage());
            printLine();
            storage.createFile();
            taskList = new TaskList();
        }
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                         INDENT, NAME, INDENT);
        System.out.println(NAME_ART);
        printLine();
        System.out.println(greeting);
        printLine();
        System.out.println();

        boolean exit = false;
        while (!exit) {
            String text = sc.nextLine();
            printLine();
            try {
                exit = parseCommand(text);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                printLine();
                System.out.println();
            }
        }
        sc.close();
    }

    private boolean parseCommand(String text) throws DukeException {
        String[] split = text.split(" ");
        if (text.isEmpty() || split.length == 0) {
            String message = String.format("%sOOPS!!! You have not entered anything!%n", INDENT);
            throw new DukeException(message);
        }

        Keyword key;
        try {
            key = Keyword.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            String str = String.format("%sOOPS!!! I'm sorry, but I don't know what that means.", INDENT);
            throw new DukeException(str);
        }

        if (split.length == 1) {
            return processOneWordCommand(key);
        }

        String rest = text.substring(split[0].length() + 1);
        return processMultiWordCommand(key, rest);
    }

    private boolean processOneWordCommand(Keyword key) throws DukeException {
        String message = String.format("%sOOPS!!! The description of a %s cannot be empty.%n%s",
                INDENT, key.getKeyword(), INDENT);
        switch (key){
        case BYE:
            System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
            return true;

        case LIST:
            taskList.listTask();
            break;

        case TODO:
            throw new TodoException(message);

        case DEADLINE:
            throw new DeadlineException(message);

        case EVENT:
            throw new EventException(message);

        case MARK:
        case UNMARK:
        case DELETE:
            throw new ManipulateException(message, key.getKeyword());
        }
        return false;
    }

    private boolean processMultiWordCommand(Keyword key, String rest) throws DukeException {
        String message = String.format("%sOOPS!!! The command for %s task is invalid.%n%s",
                INDENT, key.getKeyword(), INDENT);
        switch (key) {
        case BYE:
                if (rest.equals(NAME)) {
                    System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
                    return true;
                }
            // fall through
        case LIST:
            String errMessage = String.format("%sOOPS!!! The command for %s is invalid.\n" +
                                "%sEnter in the form: \"%s\"",
                    INDENT, key.getKeyword(), INDENT, key.getKeyword());
            throw new DukeException(errMessage);

        case MARK:
        case UNMARK:
        case DELETE:
            processManipulateCommand(key, rest, message);
            break;

        case TODO:
        case DEADLINE:
        case EVENT:
            processAddCommand(key, rest, message);
            break;
        }
        return false;
    }

    private void processManipulateCommand(Keyword key, String rest, String err) throws DukeException {
        int task_num;
        try {
            if (!rest.equals("all")) {
                task_num = Integer.parseInt(rest);
            } else {
                taskList.manipulateAllTask(key);
                storage.changeFile(key, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, key.getKeyword());
        }

        if (key.equals(Keyword.DELETE)) {
            taskList.deleteTask(task_num - 1);
            storage.changeFile(Keyword.DELETE, task_num - 1);
        } else {
            taskList.markTask(task_num - 1, key.equals(Keyword.MARK));
            storage.changeFile(key, task_num - 1);
        }
    }

    private void processAddCommand(Keyword key, String rest, String err) throws DukeException {
        Task task;
        switch(key) {
        case TODO:
            task = new Todo(rest);
            break;

        case DEADLINE:
            String[] deadlineTask = rest.split(" /by ");
            if (deadlineTask.length != 2) {
                throw new DeadlineException(err);
            }
            task = new Deadline(deadlineTask[0], deadlineTask[1]);
            break;

        default: // equivalent to case EVENT
            String[] eventTask = rest.split(" /from ");
            if (eventTask.length != 2) {
                throw new EventException(err);
            }
            String[] dates = eventTask[1].split(" /to ");
            if (dates.length != 2) {
                throw new EventException(err);
            }
            task = new Event(eventTask[0], dates[0], dates[1]);
            break;
        }
        taskList.addTask(task);
        storage.appendFile(task.fileFormat());
    }

    private static void printLine() {
        String line = "    ____________________________________________________________\n" +
                      "   /_____/_____/_____/_____/_____/_____/_____/_____/_____/_____/";
        System.out.println(line);
    }
}
