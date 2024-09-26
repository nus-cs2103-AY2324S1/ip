package seedu.duke;

/**
 * This class contains the Commands for Duke to execute.
 */
public class Command {

    /**
     * Custom exception class for handling tasks with empty descriptions.
     */
    static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String task) {
            super("OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    /**
     * Custom exception class for handling wrong command formats.
     */
    static class WrongFormatException extends Exception {
        public WrongFormatException(String message) {
            super(message);
        }
    }

    /**
     * Custom exception class for handling unknown commands.
     */
    static class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("OOPS I'm sorry, but I don't know what that means :-P");
        }
    }

    /**
     * Sorts the tasks in the provided TaskList.
     *
     * @param tasks The TaskList to be sorted.
     * @return A message indicating that the list has been sorted.
     */
    public static String sortCommand(TaskList tasks) {
        tasks.sort();
        String output = "I have sorted your list \n";
        return output + listCommand(tasks);
    }

    /**
     * Lists all tasks in the provided TaskList.
     *
     * @param tasks The TaskList to list tasks from.
     * @return A formatted string containing the list of tasks.
     */
    public static String listCommand(TaskList tasks) {
        String output = "";
        output += "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += ((i + 1) + ". " + tasks.get(i)) + "\n";
        }
        return output;
    }

    /**
     * Finds and lists tasks matching a filter word in the provided TaskList.
     *
     * @param tasks      The TaskList to search for matching tasks.
     * @param filterWord The filter word to match against task descriptions.
     * @return A formatted string containing the matching tasks.
     */
    public static String findCommand(TaskList tasks, String filterWord) {
        String output = "";
        output += "Here are the matching tasks in your list: \n";
        TaskList filteredTaskList = tasks.findmatching(filterWord.substring(5));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            output += ((i + 1) + ". " + filteredTaskList.get(i).toString()) + "\n";
        }
        return output;
    }

    /**
     * Marks or unmarks a task as done in the provided TaskList.
     *
     * @param tasks        The TaskList containing the task to be marked/unmarked.
     * @param inputParts   The input parts containing the task index.
     * @param commandType  The type of command (MARK or UNMARK).
     * @return A message indicating whether the task was marked or unmarked.
     */
    public static String markCommand(TaskList tasks, String[] inputParts, CommandType commandType) {
        try {
            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task task = tasks.get(taskIndex);
                if (commandType == CommandType.MARK) {
                    task.markAsDone();
                    return ("Nice! I've marked this task as done: " + task);
                } else {
                    task.markAsNotDone();
                    return ("OK, I've marked this task as not done yet: " + task);
                }
            } else {
                return ("Invalid task index.");
            }
        } catch (Exception e) {
            return "Invalid task index";
        }
    }

    /**
     * Deletes a task from the provided TaskList.
     *
     * @param tasks      The TaskList containing the task to be deleted.
     * @param inputParts The input parts containing the task index.
     * @return A message indicating that the task was deleted.
     */
    public static String deleteCommand(TaskList tasks, String[] inputParts) {
        assert inputParts.length >= 2 : "Invalid inputParts length";
        if (inputParts[1].equals("All")) {
            tasks.clear();
            return "I have cleared all tasks from your todo list";
        }
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            return ("Noted. I've removed this task: \n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            return "Invalid task index";
        }
    }

    /**
     * Processes the user's input and executes the corresponding command.
     *
     * @param userInput The user's input.
     * @param tasks     The TaskList to perform actions on.
     * @return The result message of the executed command.
     */
    public static String processUserInput(String userInput, TaskList tasks) {
        String[] inputParts = userInput.split(" ");
        String commandStr = inputParts[0];
        CommandType commandType = getCommandType(commandStr);

        switch (commandType) {
            case BYE:
                return Command.byeCommand();
            case LIST:
                return Command.listCommand(tasks);
            case MARK:
            case UNMARK:
                return Command.markCommand(tasks, inputParts, commandType);
            case DELETE:
                return Command.deleteCommand(tasks, inputParts);
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    return Command.taskCommand(userInput, tasks, commandType);
                } catch (WrongFormatException | EmptyDescriptionException | UnknownCommandException e) {
                    return e.getMessage();
                }
            case FIND:
                return Command.findCommand(tasks, userInput);
            case SORT:
                return Command.sortCommand(tasks);
            default:
                return "Sorry I don't know what you mean";
        }
    }

    /**
     * Creates a Todo task and adds it to the TaskList.
     *
     * @param tasks     The TaskList to add the task to.
     * @param inputParts The input parts containing the task description.
     * @param userInput The full user input.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    public static void todoCommand(TaskList tasks, String[] inputParts, String userInput) throws EmptyDescriptionException {
        if (inputParts.length <= 1) {
            throw new EmptyDescriptionException(inputParts[0]);
        }
        tasks.add(new Todo(userInput.substring(5)));
    }

    /**
     * Creates a Deadline task and adds it to the TaskList.
     *
     * @param tasks     The TaskList to add the task to.
     * @param inputParts The input parts containing the task description.
     * @param userInput The full user input.
     * @throws EmptyDescriptionException If the task description is empty.
     * @throws WrongFormatException    If the input format for Deadline is incorrect.
     */
    public static void deadlineCommand(TaskList tasks, String[] inputParts, String userInput) throws EmptyDescriptionException, WrongFormatException{
        if (inputParts.length <= 1) {
            throw new EmptyDescriptionException(inputParts[0]);
        }
        try {
            String[] deadlineParts = userInput.split(" /by ");
            String description = deadlineParts[0].substring(9);
            String by = deadlineParts[1];
            tasks.add(new Deadline(description, by));
        } catch (Exception e) {
            throw new WrongFormatException("OOPS deadlines need to be in this format, deadline return book /by YYYY-MM-DD");
        }
    }


    /**
     * Creates an Event task and adds it to the TaskList.
     *
     * @param tasks     The TaskList to add the task to.
     * @param inputParts The input parts containing the task description.
     * @param userInput The full user input.
     * @throws EmptyDescriptionException If the task description is empty.
     * @throws WrongFormatException    If the input format for Event is incorrect.
     */
    public static void eventCommand(TaskList tasks, String[] inputParts, String userInput) throws EmptyDescriptionException, WrongFormatException{
        if (inputParts.length <= 1) {
            throw new EmptyDescriptionException(inputParts[0]);
        } try {
            String[] eventParts = userInput.split(" /from | /to ");
            String description = eventParts[0].substring(6);
            String from = eventParts[1];
            String to = eventParts[2];
            tasks.add(new Event(description, from, to));
        } catch (Exception e) {
            throw new WrongFormatException("OOPS events need to be in this format, event project meeting /from YYYY-MM-DD /to YYYY-MM-DD");
        }
    }

    /**
     * Generates a goodbye message.
     * which will trigger the program in main to close
     * the window when this goodbye message is detected
     *
     * @return A farewell message.
     */
    public static String byeCommand() {
        return "Bye, this window will magically disappear in 3 seconds";
    }



    /**
     * Handles task creation commands (TODO, DEADLINE, EVENT) and adds the task to the TaskList.
     *
     * @param userInput   The full user input.
     * @param tasks       The TaskList to add the task to.
     * @param commandType The type of command (TODO, DEADLINE, EVENT).
     * @return A message indicating that the task has been added.
     * @throws EmptyDescriptionException If the task description is empty.
     * @throws UnknownCommandException   If the command type is unknown.
     * @throws WrongFormatException      If the input format for the command is incorrect.
     */
    public static String taskCommand(String userInput, TaskList tasks, CommandType commandType) throws EmptyDescriptionException, UnknownCommandException, WrongFormatException {
        String[] inputParts = userInput.split(" ");

        if (commandType == CommandType.TODO) {
            Command.todoCommand(tasks,inputParts,userInput);
        } else if (commandType == CommandType.DEADLINE) {
            Command.deadlineCommand(tasks,inputParts,userInput);
        } else if (commandType == CommandType.EVENT) {
            Command.eventCommand(tasks,inputParts,userInput);
        } else {
            throw new UnknownCommandException();
        }
        String output = "";
        output += "Got it. I've added this task: \n";
        output += "  " + tasks.get(tasks.size() - 1) + "\n";
        output += "Now you have " + tasks.size() + " tasks in the list. \n";
        return output;
    }

    /**
     * Gets the CommandType enum value based on the command string.
     *
     * @param commandStr The command string.
     * @return The CommandType enum value.
     */
    public static CommandType getCommandType(String commandString) {
        try {
            return CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Enum representing different command types.
     */
    enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, BYE, SORT, UNKNOWN
    }

}