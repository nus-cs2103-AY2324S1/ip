package duke;


/**
 * Class to handle all the various outputs of the program
 * displayed on the GUI.
 */
public class Ui {

    private static final String DASH_LINE = "----------------------------------------\n";

    /**
     * Initialises the UI.
     */
    public Ui() {}


    public String displayWelcomeMessage() {
        return "Hello! I'm Sivraj\n" + "What can I do for you?";
    }

    /**
     * Displays the message after a todo command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @return Message to be displayed.
     */
    public String displayToDoMessage(TaskList taskList) {
        return DASH_LINE + "     Got it. I've added this task:" + "\n" +
                "         " + taskList.getTask(taskList.listSize() - 1) + "\n" +
                "     Now you have " + taskList.listSize() + " tasks in the list.\n" +
                DASH_LINE;
    }

    /**
     * Displays the message after a deadline command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @return Message to be displayed.
     */
    public String displayDeadlineMessage(TaskList taskList) {
        return DASH_LINE + "     Got it. I've added this task:\n" +
                "       " + taskList.getTask(taskList.listSize() - 1) +
                "\n" + "     Now you have " + taskList.listSize() +
                " tasks in the list.\n" + DASH_LINE;
    }

    /**
     * Displays the message after an event command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @return Message to be displayed.
     */
    public String displayEventMessage(TaskList taskList) {
        return DASH_LINE + "     Got it. I've added this task:\n" +
                "       " + taskList.getTask(taskList.listSize() - 1) +
                "\n" + "     Now you have " + taskList.listSize() +
                " tasks in the list.\n" + DASH_LINE;
    }

    /**
     * Displays the message after a mark command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @param taskIndex Index of task to be marked.
     * @return Message to be displayed.
     */
    public String displayMarkMessage(TaskList taskList, int taskIndex) {
        return DASH_LINE + "    Nice! I've marked this task as done:\n" +
                "       " + taskList.getTask(taskIndex) + "\n" +
                DASH_LINE;
    }

    /**
     * Displays the message after an unmark command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @param taskIndex Index of task to be unmarked.
     * @return Message to be displayed.
     */
    public String displayUnmarkMessage(TaskList taskList, int taskIndex) {
        return DASH_LINE + "     OK, I've marked this task as not done yet:\n" +
                "       " + taskList.getTask(taskIndex) + "\n" +
                DASH_LINE;
    }

    /**
     * Displays the message after a list command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @return Complete list.
     */
    public String displayListMessage(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.listSize(); i++) {
            s = s + "\n" + (i + 1) + "." + taskList.getTask(i);
        }
        return DASH_LINE +
                "Here are the tasks in your list:" + "\n" +
                s + "\n" + DASH_LINE;
    }

    /**
     * Displays the message after a delete command is executed.
     *
     * @param taskList List where all tasks are stored.
     * @param removedTask Task that has been removed.
     * @return Message to be displayed.
     */
    public String displayDeleteMessage(TaskList taskList, Task removedTask) {
        return DASH_LINE + "     Noted. I've removed this task: \n" +
                "       " + removedTask + "\n" +
                "     Now you have " + taskList.listSize() + " tasks in the list.\n" +
                DASH_LINE;
    }

    /**
     * Displays the message if there is some error.
     *
     * @param message Error message.
     * @return Error Message.
     */
    public String displayErrorMessage(String message) {

        return DASH_LINE + "    OOPS " + message + "\n" + DASH_LINE;
    }

    /**
     * Displays the message after a bye command is executed.
     *
     * @return Message to be displayed.
     */
    public String displayByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the message if there is some error in loading the tasks.
     *
     * @param errorMessage Error Message.
     */
    public void displayLoadErrorMessage(String errorMessage) {
        System.out.println("Error loading tasks: " + errorMessage);
    }
}

