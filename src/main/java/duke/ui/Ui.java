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
     * Plays the chatbot's greeting message.
     */
    public void playGreeting() {
        System.out.println(SPACER);
        System.out.println("Howdy, I'm " + botName + ", your friendly personal assistant!");
        System.out.println("What can I do for you today?");
        System.out.println(SPACER);
    }

    /**
     * Plays the chatbot's goodbye message.
     */
    public void playGoodbye() {
        System.out.println(SPACER);
        System.out.println("See ya later, alligator! I'm waiting here if you need anything :>");
        System.out.println(SPACER);
    }

    /**
     * Plays the message after a task has been marked as completed.
     *
     * @param taskDescription The description of the Task marked.
     */
    public void playTaskComplete(String taskDescription) {
        System.out.println(SPACER);
        System.out.println("Good job on completing this task! You are an awesome possum!!");
        System.out.println(taskDescription);
        System.out.println(SPACER);
    }

    /**
     * Plays the message after a task has been unmarked as completed.
     *
     * @param taskDescription The description of the Task unmarked.
     */
    public void playTaskIncomplete(String taskDescription) {
        System.out.println(SPACER);
        System.out.println("Man, you've got this extra thing to do now...");
        System.out.println(taskDescription);
        System.out.println(SPACER);
    }

    /**
     * Plays the message after a task has been removed from the task list.
     *
     * @param taskDescription The description of the Task removed.
     * @param taskCount The total number of Tasks in the TaskList after removal.
     */
    public void playRemoveTask(String taskDescription, int taskCount) {
        System.out.println(SPACER);
        System.out.println("Guess you've got one less thing to do now.");
        System.out.println(taskDescription);
        System.out.println("Get going! You have " + taskCount + " tasks on record!!");
        System.out.println(SPACER);
    }

    /**
     * Plays the message after a task has been added to the task list.
     *
     * @param taskDescription The description of the Task added.
     * @param taskCount The total number of Tasks in the TaskList after addition.
     */
    public void playAddTask(String taskDescription, int taskCount) {
        System.out.println(SPACER);
        System.out.println("Okay, so here is the new thing to keep you occupied:");
        System.out.println(taskDescription);
        System.out.println("Get going! You have " + taskCount + " tasks on record!!");
        System.out.println(SPACER);
    }

    /**
     * Plays a message and prints the specified TaskList as a numbered list.
     *
     * @param taskList The TaskList to be printed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println(SPACER);
        System.out.println("Hey buddy, here's the stuff you need to do:");
        taskList.printTasksAsList();
        System.out.println(SPACER);
    }


    /**
     * Plays a message and prints the specified task list of search results from find to console.
     *
     * @param searchResults The TaskList containing the search results.
     */
    public void printKeywordSearchResults(TaskList searchResults) {
        System.out.println(SPACER);
        System.out.println("Alright buddy, here are the matching tasks I dug up:");
        searchResults.printTasksAsList();
        System.out.println(SPACER);
    }

    /**
     * Plays an error message with the specified exception message.
     *
     * @param exceptionMessage The ExceptionMessage to be shown.
     */
    public void playExceptionMessage(ExceptionMessage exceptionMessage) {
        System.out.println(SPACER);
        System.out.println(exceptionMessage.message);
        System.out.println(SPACER);
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
