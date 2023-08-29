package duke;

/**
 * Represents a Ui object that deals with interactions with the user.
 */
public class Ui {
    private final String DIVIDER = "\t____________________________________________________________";
    private final String LOGO = "\t░█████╗░██╗░░██╗░█████╗░████████╗████████╗██╗░░░██╗\n"
            + "\t██╔══██╗██║░░██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗░██╔╝\n"
            + "\t██║░░╚═╝███████║███████║░░░██║░░░░░░██║░░░░╚████╔╝░\n"
            + "\t██║░░██╗██╔══██║██╔══██║░░░██║░░░░░░██║░░░░░╚██╔╝░░\n"
            + "\t╚█████╔╝██║░░██║██║░░██║░░░██║░░░░░░██║░░░░░░██║░░░\n"
            + "\t░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░░░░╚═╝░░░\n";
    private final String GREETING = "\tHello! I'm Chatty.\n\tWhat can I do for you?";
    private final String FAREWELL = "\tBye. Have \"fun\" in school!";
    private final String ERROR = "\tError: ";

    /**
     * Prints the introduction message to console.
     */
    public void showIntroduction() {
        System.out.println("\n\tWelcome to Chatty.\n" + LOGO);
        sendGreeting();
    }

    private void sendGreeting() {
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    /**
     * Prints the concluding message to console.
     */
    public void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }

    /**
     * Prints a divider to console.
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the task list to console.
     * @param taskList String representation of the task list.
     */
    public void printTaskList(String taskList) {
        System.out.println("\tHere are the tasks in your list:");
        System.out.println(taskList);
    }

    /**
     * Prints the matching tasks to console.
     * @param matchingTasks String representation of the matching tasks.
     */
    public void addTaskOutputText(Task newTask, int taskListSize) {
        System.out.println("\tGot it. I've added this task:");
        // can use -1 because we just added it
        System.out.println("\t\t" + newTask.toString());
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("\tNow you have " + taskListSize + " " + taskWord + " in your list.");
    }

    /**
     * Prints the matching tasks to console.
     */
    public void showLoadingError() {
        System.out.println(ERROR + "Error loading file.");
    }

    /**
     * Prints the message when a task is marked as done to console.
     * @param taskString String representation of the task that is marked as done.
     */
    public void markTaskAsDoneMessage(String taskString) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + taskString);
    }

    /**
     * Prints the message when a task is marked as not done to console.
     * @param taskString String representation of the task that is marked as not done.
     */
    public void unmarkTaskAsDoneMessage(String taskString) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + taskString);
    }

    /**
     * Prints the error message to console.
     * @param errorMessage Error message to be printed.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }

    /**
     * Prints the message when a task is deleted to console.
     * @param taskToString String representation of the task that is deleted.
     * @param taskIndex Index of the task that is deleted.
     * @param taskListSize Size of the task list.
     */
    public void printDeleteMessage(String taskToString, int taskIndex, int taskListSize) {
        System.out.println("\tNoted. I've removed task " + (taskIndex + 1) + ":");
        System.out.println("\t\t" + taskToString);
        taskListSize -= 1;
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("\tNow you have " + taskListSize + " " + taskWord + " in your list.");
    }

}
