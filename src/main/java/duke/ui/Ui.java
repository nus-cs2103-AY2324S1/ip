package duke.ui;

import duke.tasks.TaskList;

/**
 * Encapsulates the user interface of the chatbot.
 * This class is mainly responsible for the returning the corresponding messages and responses
 * according to the user's input. What the response should be (i.e. the logic behind determining what to
 * respond given a user input) is handled by the Parser class.
 *
 * @author Wu Jingya
 */
public class Ui {
    private String botName;
    /**
     * Constructs an Ui object with the specified chatbot name.
     *
     * @param botName The chatbot's name.
     */
    public Ui(String botName) {
        this.botName = botName;
    }

    /**
     * Returns the chatbot's greeting message.
     */
    public String playGreeting() {
        return "Howdy, I'm " + botName + ", your friendly personal assistant!\n"
                + "What can I do for you today?";
    }

    /**
     * Returns the chatbot's goodbye message.
     */
    public String playGoodbye() {
        return "See ya later, alligator! I'm waiting here if you need anything :>";
    }

    /**
     * Returns the message played after a task has been marked as completed.
     *
     * @param taskDescription The description of the Task marked.
     */
    public String playTaskComplete(String taskDescription) {
        return "Good job on completing this task! You are an awesome possum!!\n"
                + taskDescription;
    }

    /**
     * Returns the message played after a task has been unmarked as completed.
     *
     * @param taskDescription The description of the Task unmarked.
     */
    public String playTaskIncomplete(String taskDescription) {
        return "Man, you've got this extra thing to do now...\n"
                + taskDescription;
    }

    /**
     * Returns the message played after a task has been removed from the task list.
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
     * Returns the message played after a task has been added to the task list.
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
     * Returns a message and prints the specified TaskList as a numbered list.
     *
     * @param taskList The TaskList to be printed.
     */
    public String printTaskList(TaskList taskList) {
        return "Hey buddy, here's the stuff you need to do:\n"
                + taskList.printTasksAsList();
    }


    /**
     * Returns a message and prints the specified task list of search results from find to console.
     *
     * @param searchResults The TaskList containing the search results.
     */
    public String printKeywordSearchResults(TaskList searchResults) {
        return "Alright buddy, here are the matching tasks I dug up:\n"
                + searchResults.printTasksAsList();
    }

    /**
     * Returns a message and prints the specified task list of search results from find to console.
     *
     * @param sortedList The new sorted TaskList.
     * @param sortOrder The sort order the list is sorted by.
     */
    public String printSortedList(TaskList sortedList, TaskList.SortOrder sortOrder) {
        return "Okie dokie, I've sorted the tasks by "
                + sortOrder.toString()
                + ":\n"
                + sortedList.printTasksAsList();
    }

    /**
     * Returns an error message with the specified exception message.
     *
     * @param exceptionMessage The ExceptionMessage to be shown.
     */
    public String playExceptionMessage(ExceptionMessage exceptionMessage) {
        return exceptionMessage.message;
    }

    /**
     * Enumeration for exception messages.
     */
    public enum ExceptionMessage {
        MARK_COMMAND_NUMBER_FORMAT_EXCEPTION(
                "HOLD UP! Invalid input for mark/unmark command. Input must be a positive non-zero integer."),
        DELETE_COMMAND_NUMBER_FORMAT_EXCEPTION(
                "HOLD UP! Invalid input for delete command. Input must be a positive non-zero integer."),
        TASK_LIST_INDEX_OUT_OF_BOUNDS_EXCEPTION(
                "HOLD UP! There is no such task in your list!"),
        ADD_TASK_MISSING_DESCRIPTION(
                "HOLD UP! So... what is this task about??"),
        ADD_TASK_DATE_TIME_PARSE_EXCEPTION(
                "HOLD UP! You are not formatting your dates right! Use \"yyyy-MM-dd HH:mm\" >:("),
        ADD_TASK_MISSING_DEADLINE(
                "HOLD UP! C'mon, what's the deadline?"),
        ADD_TASK_MISSING_START_END_DATE(
                "HOLD UP! C'mon, when does it start and end?"),
        SORT_TASKS_MISSING_SORT_ORDER(
                "HOLD UP! Okay, I know you want me to sort your tasks, but sort by WHAT?!"),
        SORT_TASKS_INVALID_SORT_ORDER(
                "HOLD UP! Sorting the tasks by whatever you said is beyond my capabilities."),
        INVALID_INPUT(
                "HOLD UP! What on earth do you mean??");

        private String message;

        ExceptionMessage(String message) {
            this.message = message;
        }
    }
}
