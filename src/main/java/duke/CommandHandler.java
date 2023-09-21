package duke;

/**
 * Handles different commands from user input.
 */
public class CommandHandler {
    private TaskList taskList;
    private Ui ui;
    private DtFormat dtf;

    /**
     * Constructor for CommandHandler class
     */
    public CommandHandler(TaskList tl, Ui ui, DtFormat dtf) {
        taskList = tl;
        this.ui = ui;
        this.dtf = dtf;
    }

    /**
     * Handles actions when user issues a 'bye' command.
     */
    public String handleByeCommand() {
        return "bye";
    }

    /**
     * Handles actions when user issues a 'list' command.
     */
    public String handleListCommand() {
        String out = ui.print("Here are the tasks in your list:");
        out += ui.printItems(taskList.getItems());
        return out;
    }

    /**
     * Handles actions when user issues a 'find' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleFindCommand(String[] splitStr) throws DukeException {
        String out = "";
        String foundTaskStr = "";
        if (splitStr.length < 2) {
            throw new DukeException("Invalid format detected for 'find' command. Enter find [search_term]");
        }
        String searchTerm = Utils.splitStringBySpaces(splitStr, 1, splitStr.length);

        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getItem(i).getTaskDesc().contains(searchTerm)) {
                foundTaskStr += (i + 1) + ". " + ui.formatTaskToPrint(taskList.getItem(i), dtf.getOutFormatter());
                foundTaskStr += "\n";
            }
        }
        out += ui.print("Here are the matching tasks in your list:");
        out += foundTaskStr;
        return out;
    }

    /**
     * Handles actions when user issues a 'mark' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleMarkCommand(String[] splitStr) throws DukeException {
        String out = "";
        if (splitStr.length != 2) {
            throw new DukeException("Invalid format detected for 'mark' command. Enter mark [item_no]");
        }
        int x = Integer.parseInt(splitStr[1]) - 1;
        if (x < 0 || x + 1 > taskList.getSize()) {
            throw new DukeException("Index is out of list range.");
        }
        taskList.getItem(x).setIsCompleted(true);
        out += ui.print("Nice! I've marked this task as done:");
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(x), dtf.getOutFormatter()));
        return out;
    }

    /**
     * Handles actions when user issues a 'unmark' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleUnmarkCommand(String[] splitStr) throws DukeException {
        String out = "";
        if (splitStr.length != 2) {
            throw new DukeException("Invalid format detected for 'unmark' command. Enter unmark [item_no]");
        }
        int x = Integer.parseInt(splitStr[1]) - 1;
        if (x < 0 || x + 1 > taskList.getSize()) {
            throw new DukeException("Index is out of list range.");
        }
        taskList.getItem(x).setIsCompleted(false);
        out += ui.print("Ok, I've marked this task as not done yet:");
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(x), dtf.getOutFormatter()));
        return out;
    }

    /**
     * Handles actions when user issues a 'remove' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleRemoveCommand(String[] splitStr) throws DukeException {
        String out = "";
        if (splitStr.length != 2) {
            throw new DukeException("Invalid format detected for 'remove' command. Enter remove [item_no]");
        }
        int x = Integer.parseInt(splitStr[1]) - 1;
        if (x < 0 || x + 1 > taskList.getSize()) {
            throw new DukeException("Index is out of list range.");
        }
        out += ui.print("Ok, the following item was removed:");
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(x), dtf.getOutFormatter()));
        taskList.removeItem(x);
        return out;
    }

    /**
     * Handles actions when user issues a 'todo' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleTodoCommand(String[] splitStr) throws DukeException {
        String out = "";
        if (splitStr.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String desc = Utils.splitStringBySpaces(splitStr, 1, splitStr.length);
        taskList.addItem(new Todo(desc, false));
        out += "Got it, I've added this task:";
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(taskList.getSize() - 1), dtf.getOutFormatter()));
        out += ui.print("Now you have " + taskList.getSize() + " tasks in the list.");
        return out;
    }

    /**
     * Handles actions when user issues a 'deadline' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleDeadlineCommand(String[] splitStr) throws DukeException {
        String out = "";
        String desc = "";
        String dateString = "";
        boolean wasByKeywordFound = false;
        for (int i = 1; i < splitStr.length; i++) {
            if (splitStr[i].equals("/by")) {
                dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                desc = Utils.splitStringBySpaces(splitStr, 1, i);
                wasByKeywordFound = true;
                break;
            }
        }
        if (!wasByKeywordFound) {
            throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
        }
        if (desc.isEmpty()) {
            throw new DukeException("Description of task cannot be empty.");
        }
        if (dateString.isEmpty()) {
            throw new DukeException("Deadline cannot be empty.");
        }
        taskList.addItem(new Deadline(desc, false, dateString, dtf.getFormatters()));

        out += ui.print("Got it, I've added this task:");
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(taskList.getSize() - 1), dtf.getOutFormatter()));
        out += ui.print("Now you have " + taskList.getSize() + " tasks in the list.");
        return out;
    }

    /**
     * Handles actions when user issues a 'event' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handleEventCommand(String[] splitStr) throws DukeException {
        String out = "";
        String desc = "";
        String dateString = "";
        boolean wasByKeywordFound = false;
        for (int i = 1; i < splitStr.length; i++) {
            if (splitStr[i].equals("/from")) {
                dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                desc = Utils.splitStringBySpaces(splitStr, 1, i);
                wasByKeywordFound = true;
                break;
            }
        }
        if (!wasByKeywordFound) {
            throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
        }
        if (desc.isEmpty()) {
            throw new DukeException("Description of task cannot be empty.");
        }
        if (dateString.isEmpty()) {
            throw new DukeException("Event dates cannot be empty.");
        }
        taskList.addItem(new Event(desc, false, dateString, dtf.getFormatters()));
        out += ui.print("Got it, I've added this task:");
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(taskList.getSize() - 1), dtf.getOutFormatter()));
        out += ui.print("Now you have " + taskList.getSize() + " tasks in the list.");
        return out;
    }

    /**
     * Handles actions when user issues a 'priority' command.
     *
     * @param splitStr user's full command, including arguments for command.
     */
    public String handlePriorityCommand(String[] splitStr) throws DukeException {
        DukeException e = new DukeException("Invalid format for command. priority "
                + "command takes the form: priority [task number] [0/1]");
        String out = "";
        if (splitStr.length != 3) {
            throw e;
        }
        int taskIndex;
        int priorityVal;
        try {
            taskIndex = Integer.valueOf(splitStr[1]) - 1;
            priorityVal = Integer.valueOf(splitStr[1]);
        } catch (NumberFormatException ne) {
            throw e;
        }
        final int PRIORITY_LOWER_BOUND = 0;
        final int PRIORITY_UPPER_BOUND = 1;
        if (priorityVal < PRIORITY_LOWER_BOUND || priorityVal > PRIORITY_UPPER_BOUND
                || taskIndex < 0 || taskIndex >= taskList.getSize()) {
            throw e;
        }
        taskList.getItem(taskIndex).setPriority(priorityVal);
        out += ui.print("Got it, I've set this task's priority to " + (priorityVal == 1 ? "high:" : "normal:"));
        out += ui.print(ui.formatTaskToPrint(taskList.getItem(taskIndex), dtf.getOutFormatter()));
        return out;

    }
}
