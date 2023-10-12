package aj;

/**
 * Ui class responsible for User Interface of the programme.
 */
public class Ui {
    private final TaskList taskList;

    Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public String horiLine() {
        return "---------------------\n";
    }

    /**
     * Returns a string which greets user
     */
    public String greet() {
        return horiLine() + "Hello! I'm Aj\n" + "Loading data....\nHere are your saved data:\n" + getListOfTasks()
                + "\nWhat can i do for you?\n" + horiLine();
    }

    /**
     * Returns a string showing help message
     *
     * @return String of help message
     */
    public String getHelpMessage() {
        return "Here is a list of commands you can try:\n\n" + "list - To list your added tasks\n" + "mark - To mark "
                + "a " + "task as completed\n" + "unmark - To unmark a task\n" + "delete - To delete a task\n" + "find "
                + "- To " + "find any keyword related to the task\n" + "todo - To create a 'Todo' task\n" + "deadline -"
                + " To " + "creete" + " a 'deadline' task\n" + "event - To create an 'event' " + "task\n"
                + "undo - To undo latest command \n" + "\n\n\n" + "To get " + "more " + "specific help for " + "each "
                + "command, type the command itself!!!\n";
    }

    /**
     * Throws error with help message if command is valid, throws NoSuchCommandException otherwise
     *
     * @param cmd First part of user input which indicates type of command.
     * @throws EmptyDescriptionException If second part of user input does not exist.
     * @throws NoSuchCommandException    If command from user input does not exist.
     */
    public void checkCommand(String cmd)
            throws EmptyDescriptionException, NoSuchCommandException { // if no arguments, give help message
        String helpMessage;
        switch (cmd) {
        case "todo":
            helpMessage = "todo <task name>";
            break;
        case "deadline":
            helpMessage = "deadline <task name> /by <YYYY-MM-DD>";
            break;
        case "event":
            helpMessage = "event <task name> /from <date/time> /to <date/time>";
            break;
        case "mark":
            helpMessage = "mark <task_no>";
            break;
        case "unmark":
            helpMessage = "unmark <task_no>";
            break;
        case "delete":
            helpMessage = "delete <task_no>";
            break;
        case "find":
            helpMessage = "find <any keyword related to the task>";
            break;
        default:
            throw new NoSuchCommandException();
        }
        throw new EmptyDescriptionException(cmd, helpMessage);
    }

    /**
     * Takes in an index 'idx' and checks if it is within range of taskList size.
     *
     * @param idx Index given by user input.
     * @throws IndexOutOfRangeException If user gives an index 'idx' bigger than taskList size.
     */
    public void checkIndex(int idx) throws IndexOutOfRangeException { // throws error if index invalid
        if (idx > this.taskList.getSize() - 1 || idx < 0) {
            throw new IndexOutOfRangeException(this.taskList.getSize());
        }
    }

    /**
     * Returns a string of number of Tasks in taskList.
     */
    public String getNumTasks() {
        return String.format("Now you have %d tasks in the list.\n", this.taskList.getSize());
    }

    /** Returns a string of the tasks in taskList. */
    public String getListOfTasks() {
        String str = "";
        if (this.taskList.getSize() == 0) {
            str = "No items yet, add something!!!\n";
        }
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            assert this.taskList.getSize() != 0 : "Tasklist size should be 0";
            str += i + "." + this.taskList.getTask(i - 1) + "\n";
        }
        return str;
    }

    /**
     * Takes in a keyword and return strings of tasks with that keyword
     *
     * @param keyword Keyword given by user input
     * @return Strings of tasks with that keyword
     */
    public String getTasksWithKeyword(String keyword) {
        StringBuilder str = new StringBuilder();
        str.append("Finding tasks with names matching : \"").append(keyword).append("\"\n");
        str.append("Here they are!!:\n");
        int no = 1;
        for (int i = 0; i < this.taskList.getSize(); i++) {
            Task task = this.taskList.getTask(i);
            if (task.getTaskName().contains(keyword)) {
                str.append(Integer.toString(no++)).append(".").append(task).append("\n");
            }
        }
        str.append(horiLine());
        return str.toString();
    }

    /**
     * Returns exit message string
     */
    public String exit() {
        horiLine();
        return "Bye. Hope to see you again soon!" + horiLine() + "\n";
    }
}
