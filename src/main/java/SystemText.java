import java.util.Scanner;

public class SystemText {
    private static final String SEPARATOR = "____________________________________________________________\n";
    private static final String GREETING = "Hello! I'm Bobi >.<, what can I do for you?\n";
    private static final String EXIT = "Bye! Hope you have a good day today :)\n";
    private static final String TASK_ADDED = "New task added to your list:\n";
    private static String TASK_COUNT;
    private static final String LIST_TASKS = "Things you need to do: \n";
    private static final String UPDATE_TASK = "OK! Your task has now been updated to: \n";

    private Scanner sc;
    private TaskList taskList;

    public SystemText(TaskList taskList) {
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
    }

    public String getInput() {
        return this.sc.nextLine();
    }

    public void stopScanner() {
        this.sc.close();
    }

    // greeting message
    public static String greeting() {
        return SEPARATOR + GREETING + SEPARATOR;
    }

    // add a task
    public String printAddTask(Task task) {
        TASK_COUNT = "You currently have " + this.taskList.getLength() + " tasks to do.\n";
        return SEPARATOR + TASK_ADDED + task.toString() + "\n" + TASK_COUNT + SEPARATOR;
    }

    // list out tasks
    public String listTasks() {
        return SEPARATOR + LIST_TASKS + this.taskList.toString() + SEPARATOR;
    }

    // Update task
    public String printUpdateTask(Task task) {
        return SEPARATOR + UPDATE_TASK + task.toString() + "\n" + SEPARATOR;
    }

    // exit message
    public static String exit() {
        return SEPARATOR + EXIT + SEPARATOR;
    }
}
