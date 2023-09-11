package friday;

import java.io.IOException;

/**
 * The Parser class is responsible for interpreting user input and executing the corresponding commands.
 */
public class Parser {

    /**
     * Saves tasks to tasks.txt.
     * If an error occurs during save operation, an error message is printed.
     *
     * @param taskList The list of tasks to save.
     * @param storage The storage object to save tasks.
     */
    private void saveTasks(String taskList, Storage storage) {
        try {
            storage.saveFile(taskList);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Processes user input and interacts with the TaskList to execute user commands.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    public String processUserCommand(String userInput, TaskList taskList, Storage storage) {
        String generalCommandsFunctionResponse = handleGeneralCommands(userInput);
        if (generalCommandsFunctionResponse != null) {
            return generalCommandsFunctionResponse;
        }
        try {
            return handleTaskCommands(userInput, taskList, storage);
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    /**
     * Handles general commands that are not task-specific.
     *
     * @param userInput The command input by the user.
     * @return A string response after processing the command or null if the command is not recognized.
     */
    private String handleGeneralCommands(String userInput) {
        if (userInput.equals("hello") || userInput.equals("hi") || userInput.equals("hey")) {
            return "Hello! How can I assist you today?";
        }

        if (userInput.contains("thank")) {
            return "You're welcome!";
        }

        if (userInput.contains("what can you do")) {
            return "I can manage tasks for you! You can add tasks, mark them as done, or delete them.";
        }

        if (userInput.contains("bye") || userInput.contains("goodbye")) {
            return "Goodbye! If you need anything else, just let me know.";
        }

        return null;
    }

    /**
     * Handles task-specific commands.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleTaskCommands(String userInput, TaskList taskList, Storage storage) {
        CommandType commandType = getCommandType(userInput);

        switch (commandType) {
            case LIST:
                return "Here are the tasks in your list:\n" + taskList.toString();

            case UNMARK:
                int unmarkTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                String unmarkMessage = taskList.unmark(unmarkTaskNumber - 1);
                saveTasks(taskList.toString(), storage);
                return "Nice! I've marked this task as not done yet: \n" + unmarkMessage;

            case MARK:
                int markTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                String markMessage = taskList.mark(markTaskNumber - 1);
                saveTasks(taskList.toString(), storage);
                return "Nice! I've marked this task as done:\n" + markMessage;

            case DELETE:
                int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                String deleteMessage = taskList.delete(deleteTaskNumber - 1);
                saveTasks(taskList.toString(), storage);
                return "Task deleted successfully!\n" + deleteMessage;

            case FIND:
                String[] findInput = userInput.split(" ", 2);
                if (findInput.length < 2 || findInput[1].trim().isEmpty()) {
                    return "Oops! Please add a keyword to search for!";
                }
                TaskList result = taskList.findTasks(findInput[1]);
                return "Here are the matching tasks in your list:\n" + result.toString();

            case TODO:
                return handleTodoCommand(userInput, taskList, storage);

            case DEADLINE:
                return handleDeadlineCommand(userInput, taskList, storage);

            case EVENT:
                return handleEventCommand(userInput, taskList, storage);

            default:
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    public enum CommandType {
        UNMARK, MARK, DELETE, FIND, TODO, DEADLINE, EVENT, LIST, UNKNOWN
    }

    /**
     * Determines the type of command based on the user input.
     *
     * @param userInput The command input by the user.
     * @return The type of command as a string.
     */
    private CommandType getCommandType(String userInput) {
        if (userInput.contains("list")) {
            return CommandType.LIST;
        }
        else if (userInput.contains("unmark")) {
            return CommandType.UNMARK;
        }
        else if (userInput.contains("mark")) {
            return CommandType.MARK;
        }
        else if (userInput.contains("delete")) {
            return CommandType.DELETE;
        }
        else if (userInput.contains("find")) {
            return CommandType.FIND;
        }
        else if (userInput.contains("todo")) {
            return CommandType.TODO;
        }
        else if (userInput.contains("deadline")) {
            return CommandType.DEADLINE;
        }
        else if (userInput.contains("event")) {
            return CommandType.EVENT;
        }
        return CommandType.UNKNOWN;
    }

    /**
     * Handles the 'todo' command.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleTodoCommand(String userInput, TaskList taskList, Storage storage) {
        String[] todoInput = userInput.split(" ", 2);
        if (todoInput.length < 2 || todoInput[1].trim().isEmpty()) {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
        Todo todo = new Todo(todoInput[1]);
        taskList.add(todo);
        saveTasks(taskList.toString(), storage);
        return "added: " + todo.toString();
    }

    /**
     * Handles the 'deadline' command.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleDeadlineCommand(String userInput, TaskList taskList, Storage storage) {
        String[] commandAndDetails = userInput.split(" ", 2);
        if (commandAndDetails.length < 2 || !userInput.contains("/by")) {
            return "Incorrect format for 'deadline'. Here is a sample:\ndeadline return book /by Sunday";
        }
        String[] taskAndDate = commandAndDetails[1].split(" /by ", 2);
        if (taskAndDate.length < 2) {
            return "Please provide both a task description and a deadline date.";
        }
        String taskDescription = taskAndDate[0];
        String deadlineDate = taskAndDate[1];
        Deadline deadline = new Deadline(taskDescription, deadlineDate);
        taskList.add(deadline);
        saveTasks(taskList.toString(), storage);
        return "added: " + deadline.toString();
    }

    /**
     * Handles the 'event' command.
     *
     * @param userInput The command input by the user.
     * @param taskList The list of tasks.
     * @param storage The storage object to save tasks.
     * @return A string response after processing the command.
     */
    private String handleEventCommand(String userInput, TaskList taskList, Storage storage) {
        String[] commandAndDetails = userInput.split(" ", 2);
        if (commandAndDetails.length < 2 || !userInput.contains("/from") || !userInput.contains("/to")) {
            return "Incorrect format for 'event'. Expected format: event TASK_DESCRIPTION /from START_TIME /to END_TIME";
        }
        String[] taskAndTimes = commandAndDetails[1].split(" /from | /to ", 3);
        if (taskAndTimes.length < 3) {
            return "Please provide a task description, start time, and end time.";
        }
        String taskDescription = taskAndTimes[0];
        String startTime = taskAndTimes[1];
        String endTime = taskAndTimes[2];
        Event event = new Event(taskDescription, startTime, endTime);
        taskList.add(event);
        saveTasks(taskList.toString(), storage);
        return "added: " + event.toString();
    }
}
