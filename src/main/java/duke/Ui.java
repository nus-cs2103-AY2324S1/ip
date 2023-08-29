package duke;
public class Ui {
    private final String DIVIDER = "\t____________________________________________________________";
    private final String LOGO = "\t░█████╗░██╗░░██╗░█████╗░████████╗████████╗██╗░░░██╗\n" +
            "\t██╔══██╗██║░░██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗░██╔╝\n" +
            "\t██║░░╚═╝███████║███████║░░░██║░░░░░░██║░░░░╚████╔╝░\n" +
            "\t██║░░██╗██╔══██║██╔══██║░░░██║░░░░░░██║░░░░░╚██╔╝░░\n" +
            "\t╚█████╔╝██║░░██║██║░░██║░░░██║░░░░░░██║░░░░░░██║░░░\n" +
            "\t░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░░░░╚═╝░░░\n";
    private final String GREETING = "\tHello! I'm Chatty.\n\tWhat can I do for you?";
    private final String FAREWELL = "\tBye. Have \"fun\" in school!";
    private final String ERROR = "\tError: ";

    public void showIntroduction() {
        System.out.println("\n\tWelcome to Chatty.\n" + LOGO);
        sendGreeting();
    }

    private void sendGreeting() {
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printTaskList(String taskList) {
        System.out.println("\tHere are the tasks in your list:");
        System.out.println(taskList);
    }

    public void addTaskOutputText(Task newTask, int taskListSize) {
        System.out.println("\tGot it. I've added this task:");
        // can use -1 because we just added it
        System.out.println("\t\t" + newTask.toString());
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("\tNow you have " + taskListSize + " " + taskWord + " in your list.");
    }

    public void showLoadingError() {
        System.out.println(ERROR + "Error loading file.");
    }

    public void markTaskAsDoneMessage(String taskString) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + taskString);
    }

    public void unmarkTaskAsDoneMessage(String taskString) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + taskString);
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }

    public void printDeleteMessage(String taskToString, int taskIndex, int taskListSize) {
        System.out.println("\tNoted. I've removed task " + (taskIndex + 1) + ":");
        System.out.println("\t\t" + taskToString);
        taskListSize -= 1;
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("\tNow you have " + taskListSize + " " + taskWord + " in your list.");
    }

}
