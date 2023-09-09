package duke.ui;

import duke.tasks.TaskList;

/**
 * Encapsulates the user interface of the chatbot.
 * This class is mainly responsible for the printing of messages and responses to the console
 * according to the user's input. What is printed (i.e. the logic behind determining what to
 * print given a user input) is handled by the Parser class.
 *
 * @author Wu Jingya
 */
public class Ui {
    private String botName;
    private final static String SPACER = "--------------------------------------------------------------------------";

    /**
     * Constructs an Ui object with the specified chatbot name.
     *
     * @param botName The chatbot's name.
     */
    public Ui(String botName) {
        this.botName = botName;
    }

    /**
     * Gets the chatbot's greeting message.
     */
    public String playGreeting() {
        return "Howdy, I'm " + botName + ", your friendly personal assistant!\n"
                + "What can I do for you today?";
    }

    /**
     * Plays the chatbot's goodbye message.
     */
    public String playGoodbye() {
        return "See ya later, alligator! I'm waiting here if you need anything :>";
    }

    /**
     * Plays the message after a task has been marked as completed.
     *
     * @param taskDescription The description of the Task marked.
     */
    public String playTaskComplete(String taskDescription) {
        return "Good job on completing this task! You are an awesome possum!!\n"
                + taskDescription;
    }

    /**
     * Plays the message after a task has been unmarked as completed.
     *
     * @param taskDescription The description of the Task unmarked.
     */
    public String playTaskIncomplete(String taskDescription) {
        return "Man, you've got this extra thing to do now...\n"
                + taskDescription;
    }

    /**
     * Plays the message after a task has been removed from the task list.
     *
     * @param taskDescription The description of the Task removed.
     * @param taskCount The total number of Tasks in the TaskList after removal.
     */
    public String playRemoveTask(String taskDescription, int taskCount) {
        return "Guess you've got one less thing to do now.\n"
                + taskDescription + "\n"
                + "Get going! You have " + taskCount + " tasks on record!!";
    }

    /**
     * Plays the message after a task has been added to the task list.
     *
     * @param taskDescription The description of the Task added.
     * @param taskCount The total number of Tasks in the TaskList after addition.
     */
    public String playAddTask(String taskDescription, int taskCount) {
        return "Okay, so here is the new thing to keep you occupied:\n"
                + taskDescription + "\n"
                + "Get going! You have " + taskCount + " tasks on record!!";
    }

    /**
     * Plays a message and prints the specified TaskList as a numbered list.
     *
     * @param taskList The TaskList to be printed.
     */
    public String printTaskList(TaskList taskList) {
        return "Hey buddy, here's the stuff you need to do:\n"
                + taskList.printTasksAsList();
    }


    /**
     * Plays a message and prints the specified task list of search results from find to console.
     *
     * @param searchResults The TaskList containing the search results.
     */
    public String printKeywordSearchResults(TaskList searchResults) {
        return "Alright buddy, here are the matching tasks I dug up:\n"
                + searchResults.printTasksAsList();
    }

    /**
     * Plays an error message with the specified exception message.
     *
     * @param exceptionMessage The ExceptionMessage to be shown.
     */
    public String playExceptionMessage(ExceptionMessage exceptionMessage) {
        return exceptionMessage.message;
    }

    public enum ExceptionMessage {
        MarkCommand_NumberFormatException(
                "HOLD UP! Invalid input for mark/unmark command. Input must be a positive non-zero integer."),
        DeleteCommand_NumberFormatException(
                "HOLD UP! Invalid input for delete command. Input must be a positive non-zero integer."),
        TaskList_IndexOutOfBoundsException(
                "HOLD UP! There is no such task in your list!"),
        AddTask_MissingDescription(
                "HOLD UP! So... what is this task about??"),
        AddTask_DateTimeParseException(
                "HOLD UP! You are not formatting your dates right! Use \"yyyy-MM-dd HH:mm\" >:("),
        AddTask_MissingDeadline(
                "HOLD UP! C'mon, what's the deadline?"),
        AddTask_MissingStartEndDate(
                "HOLD UP! C'mon, when does it start and end?"),
        InvalidInput(
                "HOLD UP! What on earth do you mean??");

        public String message;

        ExceptionMessage(String message) {
            this.message = message;
        }
    }
}
