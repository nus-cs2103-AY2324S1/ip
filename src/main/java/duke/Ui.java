package duke;

import java.util.ArrayList;

/**
 * The {@code Ui} class. Deals with interactions with the user.
 */
public class Ui {

    private static final String name = "Leon";
    private static final String taskDescription = "task - Create a new task" + "\n";
    private static final String todoDescription = "todo - Create a new todo" + "\n";
    private static final String deadlineDescription = "deadline - Create a new deadline" + "\n";
    private static final String eventDescription = "event - Create a new event" + "\n";
    private static final String listDescription =
        "list - View your current tasks and completion status" + "\n";
    private static final String markDescription = "mark - Mark a task as complete" + "\n";
    private static final String unmarkDescription = "unmark - Mark a task as incomplete" + "\n";
    private static final String deleteDescription = "delete - Delete a task" + "\n";
    private static final String byeDescription = "bye - Exit the program" + "\n";
    private static final String findDescription = "find - Find all tasks containing a given keyword" + "\n";
    private static final String commands = taskDescription + todoDescription + deadlineDescription
        + eventDescription + listDescription + markDescription + unmarkDescription
        + deleteDescription + byeDescription + findDescription;

    public Ui() {
    }

    /**
     * Prints the available commands for the user, when {@code Duke} is started or
     * when the user inputs {@code commands}.
     */
    public String getCommands() {
        return commands;
    }

    /**
     * Prints the statements when {@code Duke} is first instantiated.
     */
    public static String getSelfIntroduction() {
        String intro1 = String.format("I'm %s. Nice to meet you." + "\n", name);
        String intro2 = "I support the following commands:" + "\n";
        return intro1 + intro2 + commands;
    }

    /**
     * Prints the statements when a user operation is over.
     */
    public String getEndOfOperationMessage() {
        String msg1 = "Anything else you want me to do?" + "\n";
        String msg2 = "Just so you know, you can input commands to view the commands again.";
        return msg1 + msg2;
    }

    public String getInvalidInputMessage(String message) {
        return String.format("I'm just a robot!%n" + "I don't understand what %s is!", message);
    }

    public String getDetailsPrompt(Parser.Command command) {
        String taskType = command.toString().toLowerCase();
        return String.format("Input %s details.", taskType);
    }

    public String getListSummary(int numOfTasks, int numOfCompletedTasks) {
        return String.format("You have %d tasks. (%d complete, %d incomplete)%n",
            numOfTasks, numOfCompletedTasks, numOfTasks - numOfCompletedTasks);
    }

    public String getTasksInList(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            result.append(String.format("%d. " + taskList.get(i).toString()
                + "%n", i + 1));
        }
        return result.toString();
    }

    public String getEmptyInputMessage(Parser.Command command, Parser.Operation operation) {
        String taskType = command.toString().toLowerCase();
        String inputType = operation.toString().toLowerCase();
        return String.format("The %s %s cannot be empty.%n", taskType, inputType);
    }

    public String getDuplicateTasksMessage(String message) {
        return String.format("Task %s already exists.%n", message);
    }

    public String getDateInputMessage(Parser.Command command, String input) {
        String taskType = command.toString().toLowerCase();
        return String.format("Input %s %s date. (Required format: YYYY-MM-DD)%n",
            taskType, input);
    }

    public String getTimeInputMessage(Parser.Command command, String input) {
        String taskType = command.toString().toLowerCase();
        return String.format("Input %s %s time. (Required format: HH:MM)%n",
            taskType, input);
    }

    public String getInvalidFormatMessage(Parser.Operation operation) {
        String inputType = operation.toString().toLowerCase();
        return String.format("Invalid %s format. Please try again.", inputType);
    }

    public String getConfirmationMessage(Parser.Command command) {
        String operation = command.toString().toLowerCase();
        return String.format("Please input the task number you wish to %s.%n",
            operation);
    }

    public String getRequestFailedMessage(String reason) {
        return String.format("Request unsuccessful. (reason: invalid %s)", reason);
    }

    public String getTaskAddedMessage(String details) {
        return String.format("Don't expect me to %s for you!%n", details);
    }

    public String getTodoAddedMessage(String details) {
        return String.format("Stop talking to me! Go and %s!%n", details);
    }

    public String getDeadlineAddedMessage(String details) {
        return String.format("Just saying, better %s now.%n"
            + "Not like it's my problem if you don't.%n", details);
    }

    public String getEventAddedMessage(String details) {
        return String.format("Wow, you have a %s?%n"
            + "Uhh, n-not like I wanna join you!%n", details);
    }

    public String getNotAllCompleteMessage() {
        return "Don't expect me to remember them for you!";
    }

    public String getAllCompleteMessage() {
        return "You've completed all your tasks. Good for you.";
    }

    public String getMarkMessage(Parser.Command command, int taskNumber) {
        String markType = "";
        if (command == Parser.Command.MARK) {
            markType = "complete";
        } else if (command == Parser.Command.UNMARK) {
            markType = "incomplete";
        }
        return String.format("Task %d set as %s.%n", taskNumber, markType);
    }

    public String getMarkRedundantMessage(Parser.Command command, int taskNumber) {
        String markType = "";
        if (command == Parser.Command.MARK) {
            markType = "complete";
        } else if (command == Parser.Command.UNMARK) {
            markType = "incomplete";
        }
        return String.format("Task %d is already %s.%n Stop wasting my time!%n", taskNumber, markType);
    }

    public String getTasksEmptyMessage(Parser.Command command) {
        String operation = command.toString().toLowerCase();
        return String.format("No tasks to %s.%n Please create a task first.", operation);
    }

    public String getTaskDeletedMessage(TaskList taskList, int taskNumber) {
        return String.format("Task %d deleted successfully.%n You now have %d tasks.%n",
            taskNumber, taskList.getNumOfTasks());
    }

    public String getKeywordMessage() {
        return "Please input search keyword.";
    }

    public String getEmptySearchResultsMessage(String keyword) {
        return String.format("No tasks containing your keyword \"%s\". Please try again.%n", keyword);
    }

    public String getSearchResultsMessage(ArrayList<Task> matchingTasks) {
        StringBuilder res = new StringBuilder(String.format("Here are the matching tasks in your list:%n"));
        for (int i = 0; i < matchingTasks.size(); i++) {
            res.append(String.format("%d. " + matchingTasks.get(i).toString()
                + "%n", i + 1));
        }
        return res.toString();
    }

    public String getInvalidStartMessage(Parser.Command command) {
        String taskType = command.toString().toLowerCase();
        return String.format("Cannot create %s before the current time. "
            + "Please try again.%n", taskType);
    }

    public String getInvalidIntervalMessage(Parser.Command command) {
        String taskType = command.toString().toLowerCase();
        return String.format("Invalid %s time interval. Please try again.%n", taskType);
    }

    public String getExitMessage(int status) {
        if (status == 0) {
            return "Finally I can rest. Bye!";
        } else if (status == 1) {
            return String.format("I've had enough of your nonsense!%n Don't let me see you again!");
        } else {
            return "An unexpected error occurred.";
        }
    }
}
