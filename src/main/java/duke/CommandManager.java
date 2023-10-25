package duke;

public class CommandManager {

    private Ui ui;
    private String input;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    public CommandManager(Ui ui, String input,
                          TaskList taskList, Parser parser, Storage storage) {
        this.ui = ui;
        this.input = input;
        this.taskList = taskList;
        this.parser = parser;
        this.storage = storage;
    }

    public String execute(Parser.CommandType commandType) {
        switch (commandType) {
            case BYE:
                return executeByeCommand();
            case LIST:
                return executeListCommand();
            case MARK:
                return executeMarkCommand();
            case UNMARK:
                return executeUnmarkCommand();
            case FIND:
                return executeFindCommand();
            case TODO:
                return executeTodoCommand();
            case EVENT:
                return executeEventCommand();
            case DEADLINE:
                return executeDeadlineCommand();
            case DELETE:
                return executeDeleteCommand();
            case INVALID:
                return "I'm sorry, I don't understand that command.";
            case HELP:
                return ui.showHelp();
            default:
                return "Unknown command.";
        }
    }


    private String executeMarkCommand() {
        try {
            int markId = Integer.parseInt(input.substring(5).trim());
            String markMessage = taskList.markTaskAsDone(markId);
            return markMessage;
        } catch (NumberFormatException e) {
            return "Invalid format. Enter an integer within the valid range.";
        }
    }

    private String executeUnmarkCommand() {
        try {
            int unmarkId = Integer.parseInt(input.substring(7).trim());
            String unmarkMessage = taskList.unmarkTask(unmarkId);
            return unmarkMessage;
        } catch (NumberFormatException e) {
            return "Invalid format. Enter an integer within the valid range.";
        }
    }

    private String executeFindCommand() {
        String keyword = input.substring(5).trim();
        return taskList.findTasks(keyword);
    }

    private String executeTodoCommand() {
        String todoDescription = input.substring(5);
        Task toDo = parser.addTodoTask(todoDescription);
        String todoMessage = taskList.addTask(toDo);
        return todoMessage;
    }

    private String executeEventCommand() {
        String[] eventParts = input.split("/from|/to");
        if (eventParts.length == 3) {
            String eventDescription = eventParts[0].substring(6).trim();
            String from = eventParts[1].trim();
            String to = eventParts[2].trim();
            Task event = parser.addEventTask(eventDescription, from, to);
            String eventMessage = taskList.addTask(event);
            return eventMessage;
        } else {
            return "Invalid event format. Use 'event <description> /from <datetime> /to <datetime>'.";
        }
    }

    private String executeDeadlineCommand() {
        String[] deadlineParts = input.split("/by", 2);
        if (deadlineParts.length == 2) {
            String deadlineDescription = deadlineParts[0].substring(9).trim();
            String dateTimeString = deadlineParts[1].trim();
            Task deadline = parser.addDeadlineTask(deadlineDescription, dateTimeString);
            String deadlineMessage = taskList.addTask(deadline);
            return deadlineMessage;
        } else {
            return "Invalid deadline format. Use 'deadline <description> /by <datetime>'.";
        }
    }

    private String executeDeleteCommand() {
        try {
            int deleteId = Integer.parseInt(input.substring(7));
            String deleteMessage = taskList.deleteTask(deleteId);
            return deleteMessage;
        } catch (NumberFormatException e) {
            return "Invalid format. Enter an integer within the valid range.";
        }
    }

    public String executeListCommand() {
        return taskList.listTasks();
    }

    public String executeByeCommand() {
        try {
            storage.save(taskList.getTaskArrayList());
            return ui.exit();
        } catch (CustomException e) {
            return "Error saving tasks: " + e.getMessage();
        }
    }

}
