public class RenUi {
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private static String welcomeMsg = LINE_BREAK +
            " Hello! I'm Ren\n" +
            " What can I do for you?\n" +
            LINE_BREAK;
    private static String goodbyeMsg = LINE_BREAK +
            " Bye! Pleasure speaking with you :) \n" +
            LINE_BREAK;

    public static void printWelcomeMsg() {
        System.out.println(welcomeMsg);
    }

    public static void printGoodbyeMsg() {
        System.out.println(goodbyeMsg);
    }

    public static void requestProperCommand() {
        System.out.println(LINE_BREAK);
        System.out.println("Please Enter a valid command.");
        System.out.println(LINE_BREAK);
    }

    public static void declareTaskAdded(Task task, TaskList tasks) {
        System.out.println(LINE_BREAK +
                String.format("Added %s\n", task) +
                tasks.declareNumOfTasks() +
                LINE_BREAK);
    }

    public static void declareTaskDeleted(Task task, TaskList tasks) {
        System.out.println(LINE_BREAK +
                String.format("Deleted %s\n", task) +
                tasks.declareNumOfTasks() +
                LINE_BREAK);
    }

    public static void declareTaskUpdated(Task task, TaskList tasks, boolean isDone) {
        System.out.println(LINE_BREAK +
                String.format("Marked as %s!\n %s\n",
                        isDone ? "done" : "undone",
                        task) +
                LINE_BREAK);
    }

    public static void listTasks(TaskList tasks) {
        System.out.println(LINE_BREAK);
        tasks.listTasks();
        System.out.println(LINE_BREAK);
    }

    public static void printException(Exception e) {
        System.out.println(LINE_BREAK);
        System.out.println(e.getMessage());
        System.out.println(LINE_BREAK);
    }
}
