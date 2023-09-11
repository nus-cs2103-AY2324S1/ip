package duke;

/**
 * Represents a Ui object that deals with interactions with the user.
 */
public class Ui {
    private final String DIVIDER = "____________________________________________________________";
    private final String LOGO = "░█████╗░██╗░░██╗░█████╗░████████╗████████╗██╗░░░██╗\n"
            + "██╔══██╗██║░░██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗░██╔╝\n"
            + "██║░░╚═╝███████║███████║░░░██║░░░░░░██║░░░░╚████╔╝░\n"
            + "██║░░██╗██╔══██║██╔══██║░░░██║░░░░░░██║░░░░░╚██╔╝░░\n"
            + "╚█████╔╝██║░░██║██║░░██║░░░██║░░░░░░██║░░░░░░██║░░░\n"
            + "░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░░░░╚═╝░░░\n";
    private final String GREETING = "Hello! I'm Chatty.\nWhat can I do for you?";
    private final String FAREWELL = "Bye. Have \"fun\" in school!";
    private final String ERROR = "Error: ";

    /**
     * Prints the introduction message to console.
     */
    public String showIntroduction() {
        // System.out.println("\nWelcome to Chatty.\n" + LOGO);
        return GREETING;
    }

    // private String sendGreeting() {
    //     // System.out.println(DIVIDER);
    //     // System.out.println(GREETING);
    //     // System.out.println(DIVIDER);
    //     return GREETING;

    // }

    /**
     * Prints the concluding message to console.
     */
    public String sendFarewell() {
        // System.out.println(DIVIDER);
        // System.out.println(FAREWELL);
        // System.out.println(DIVIDER);
        return FAREWELL;
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
    public String printTaskList(String taskList) {
        assert taskList != null : "Task list cannot be null";
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
        return "Here are the tasks in your list:\n" + taskList;
    }

    /**
     * Prints the matching tasks to console.
     * @param matchingTasks String representation of the matching tasks.
     */
    public String addTaskOutputText(Task newTask, int taskListSize) {
        assert newTask != null : "Task cannot be null";
        System.out.println("Got it. I've added this task:");
        // can use -1 because we just added it
        System.out.println("" + newTask.toString());
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("Now you have " + taskListSize + " " + taskWord + " in your list.");
        return "Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " 
                + taskListSize + " " + taskWord + " in your list.";
    }

    /**
     * Prints the matching tasks to console.
     */
    public String showLoadingError() {
        System.out.println(ERROR + "Error loading file.");
        return ERROR + "Error loading file.";
    }

    /**
     * Prints the message when a task is marked as done to console.
     * @param taskString String representation of the task that is marked as done.
     */
    public String markTaskAsDoneMessage(String taskString) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("" + taskString);
        return "Nice! I've marked this task as done:\n" + taskString;
    }

    /**
     * Prints the message when a task is marked as not done to console.
     * @param taskString String representation of the task that is marked as not done.
     */
    public String unmarkTaskAsDoneMessage(String taskString) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("" + taskString);
        return "OK, I've marked this task as not done yet:\n" + taskString;
    }

    /**
     * Prints the error message to console.
     * @param errorMessage Error message to be printed.
     */
    public String showErrorMessage(String errorMessage) {
        System.out.println(ERROR + errorMessage);
        return ERROR + errorMessage;
    }

    /**
     * Prints the message when a task is deleted to console.
     * @param taskToString String representation of the task that is deleted.
     * @param taskIndex Index of the task that is deleted.
     * @param taskListSize Size of the task list.
     */
    public String printDeleteMessage(String taskToString, int taskIndex, int taskListSize) {
        System.out.println("Noted. I've removed task " + (taskIndex + 1) + ":");
        System.out.println("" + taskToString);
        taskListSize -= 1;
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("Now you have " + taskListSize + " " + taskWord + " in your list.");
        return "Noted. I've removed task " + (taskIndex + 1) + ":\n" + taskToString + "\nNow you have "
                + taskListSize + " " + taskWord + " in your list.";
    }

    /**
     * Prints the message when a task is found to console.
     * 
     * @param taskToString String representation of the task.
     * @param priority Priority of the task based on console input.
     * @return String String representation of the task that is found.
     */
    public String printPriorityMessage(String taskToString, int priority) {
        System.out.println("Noted. I've set the priority of task " + (priority + 1) + " to " + priority + ":");
        System.out.println("" + taskToString);
        return "Noted. I've set the priority of task " + (priority + 1) + " to " + priority + ":\n" + taskToString;
    }

}
