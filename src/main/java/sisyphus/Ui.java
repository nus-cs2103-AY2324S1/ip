package sisyphus;

import sisyphus.task.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String NAME = "sisyphus.Sisyphus";
    private static final String HORIZONTAL_LINE = "_________________________________";
    private static final String LOGO = "\n" +
            "      ,-'\"\"\"`-.\n" +
            "    ,'         `.\n" +
            "   /        `    \\\n" +
            "  (    /          )\n" +
            "  |             \" |\n" +
            "  (               )\n" +
            " `.\\\\          \\ /\n" +
            "   `:.     , \\ ,\\ _\n" +
            " WE   `:-.___,-`-.{\\)\n" +
            " MUST  `.        |/ \\\n" +
            " GO      `.        \\ \\\n" +
            " ON        `-.     _\\,)\n" +
            "              `.  |,-||\n" +
            "                `.|| ||\n";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal dashed line. Used for separators.
     */
    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Reads the next line by scanner.
     *
     * @return line read by scanner
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Greets user with name.
     */
    public static void greet() {
        System.out.println(LOGO);
        System.out.println("Hello, I'm " + NAME + ".");
        System.out.println("What can I do for you? Do you want to roll a boulder?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Closes scanner and prints goodbye message.
     */
    public void exit() {
        scanner.close();
        printHorizontalLine();
        System.out.println("Goodbye, it was nice chatting with you.");
        printHorizontalLine();
    }

    /**
     * Prints all the tasks in the given TaskList in a numbered list.
     *
     * @param taskList
     */
    public static void printTasks(TaskList taskList) {
        printHorizontalLine();
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        printHorizontalLine();
    }

    public static void printMatchingTasks(TaskList taskList, String keyword) {
        printHorizontalLine();
        System.out.println("Below is the list of tasks with keyword - \"" + keyword + "\" :");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        printHorizontalLine();
    }


    /**
     * Prints the marked task and the corresponding message.
     *
     * @param taskList
     * @param index
     */
    public static void printMarkTask(TaskList taskList, int index) {
        printHorizontalLine();
        System.out.println("The following item has been marked as done.");
        System.out.println(taskList.getTask(index));
    }


    /**
     * Prints the unmarked task and the corresponding message.
     *
     * @param taskList
     * @param index
     */
    public static void printUnmarkTask(TaskList taskList, int index) {
        printHorizontalLine();
        System.out.println("The following item has been unmarked and is now uncompleted.");
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the task to be deleted and the corresponding message.
     *
     * @param taskList
     * @param index
     */
    public static void printDeleteTask(TaskList taskList, int index) {
        printHorizontalLine();
        System.out.println("The following item has been deleted from the list.");
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the most recently added ToDo and a corresponding message.
     *
     * @param taskList
     */
    public static void printAddTodo(TaskList taskList) {
        printHorizontalLine();
        System.out.println("The following ToDo has been added.");
        System.out.println(taskList.getLastTask());
        System.out.println("You now have " + taskList.getSize() + " items in the list.");
        printHorizontalLine();
    }


    /**
     * Prints the most recent added deadline and a corresponding message.
     *
     * @param taskList
     */
    public static void printAddDeadline(TaskList taskList) {
        printHorizontalLine();
        System.out.println("The following deadline has been added.");
        System.out.println(taskList.getLastTask());
        System.out.println("You now have " + taskList.getSize() + " items in the list.");
        printHorizontalLine();
    }

    /**
     * Prints the most recent added event and a corresponding message.
     *
     * @param taskList
     */
    public static void printAddEvent(TaskList taskList) {
        printHorizontalLine();
        System.out.println("The following event has been added.");
        System.out.println(taskList.getLastTask());
        System.out.println("You now have " + taskList.getSize() + " items in the list.");
        printHorizontalLine();
    }

}
