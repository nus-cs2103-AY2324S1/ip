import java.util.List;

public class Ui {
    /** Divider constant */
    private static final String DIVIDER = "      ____________________________________________________________";

    public static void showLine() {
        System.out.println(DIVIDER);
    }
    public static void showWelcome() {
        showLine();
        System.out.println("        Hello! I'm Valerie!");
        System.out.println("        What can I do for you?");
        showLine();
    }

    public static void showError(String error) {
        showLine();
        System.out.println("        ERROR: " + error + "!");
        showLine();
    }

    public static void showMarkedTask(Task task) {
        Ui.showLine();
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("            " + task);
        Ui.showLine();
    }

    public static void showUnmarkedTask(Task task) {
        showLine();
        System.out.println("         OK! I've marked this task as not done yet:");
        System.out.println("            " + task);
        showLine();
    }

    public static void showAddedTask(Task task, TaskList taskList) {
        Ui.showLine();
        System.out.println("        Got it! I've added this task: ");
        System.out.println("            " + task);
        System.out.println("        Now you have " + taskList.getSize() + " tasks in the list.");
        Ui.showLine();
    }

    public static void showDeletedTask(Task task, TaskList taskList) {
        Ui.showLine();
        System.out.println("        Noted. I've removed this task:");
        System.out.println("            " + task);
        System.out.println("        Now you have " + taskList.getSize() + " tasks in the list.");
        Ui.showLine();
    }

    public static void showList(TaskList taskList) {
        Ui.showLine();
        System.out.println("        Sure! Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            String str = String.format("            %d. %s", i + 1, taskList.getTask(i));
            System.out.println(str);
        }
        Ui.showLine();
    }

    public static void showExit() {
        showLine();
        System.out.println("        Bye ~ Hope to see you again soon ~");
        showLine();
    }
}
