package pooh;

public class Ui {
    private static final String HORIZONTAL_LINE = "      " +
            "_______________________________________________________________________________";

    /**
     * Prints the welcome message when the chatbot starts.
     */
    public static void printWelcomeMsg() {
        String logo = "      .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "      | .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "      | |   ______     | || |     ____     | || |     ____     | || |  ____  ____  | |\n" +
                "      | |  |_   __ \\   | || |   .'    `.   | || |   .'    `.   | || | |_   ||   _| | |\n" +
                "      | |    | |__) |  | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |__| |   | |\n" +
                "      | |    |  ___/   | || |  | |    | |  | || |  | |    | |  | || |   |  __  |   | |\n" +
                "      | |   _| |_      | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  | |_  | |\n" +
                "      | |  |_____|     | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                "      | |              | || |              | || |              | || |              | |\n" +
                "      | '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                "       '----------------'  '----------------'  '----------------'  '----------------'";

        String greetings = "      Hi there! Good to see you! I'm Pooh!\n      What can I do for you?";
        System.out.println(logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(greetings);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the exit message when the user terminates the chatbot.
     */
    public static void printExitMsg() {
        String byeMessage = "      How lucky I am to have something that makes saying goodbye so hard. Bye!";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(byeMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a general response message.
     *
     * @param message The message to be displayed.
     */
    public static void respond(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks.
     */
    public static void printTasksMsg(TaskList taskList) {
        StringBuilder todoListString = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            String task;
            try {
                task = String.format("      %d. ", i + 1) + taskList.getTask(i) + "\n";
            } catch (InvalidTaskException e) {
                throw new RuntimeException(e);
            }
            todoListString.append(task);
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println("      Here are the tasks in your list:");
        System.out.println(todoListString.toString().stripTrailing());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public static void printTaskDoneMsg(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("      Nice! I've marked this task as done:\n      " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public static void printTaskUndoneMsg(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("      OK, I've marked this task as not done yet:\n      " + task);
        System.out.println(HORIZONTAL_LINE);
    }

}
