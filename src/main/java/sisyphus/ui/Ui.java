package sisyphus.ui;

import java.util.Scanner;

import sisyphus.task.TaskList;

/**
 * class for UI to print pre-set messages.
 */
public class Ui {

    /** Name of the chatbot */
    private static final String NAME = "Sisyphus";
    /** Horizontal Line string */
    private static final String HORIZONTAL_LINE = "_________________________________ \n";
    /** ASCII art that represents sisyphus */
    private static final String LOGO = "\n"
            + "      ,-'\"\"\"`-.\n"
            + "    ,'         `.\n"
            + "   /        `    \\\n"
            + "  (    /          )\n"
            + "  |             \" |\n"
            + "  (               )\n"
            + " `.\\\\          \\ /\n"
            + "   `:.     , \\ ,\\ _\n"
            + " WE   `:-.___,-`-.{\\)\n"
            + " MUST  `.        |/ \\\n"
            + " GO      `.        \\ \\\n"
            + " ON        `-.     _\\,)\n"
            + "              `.  |,-||\n"
            + "                `.|| ||\n";

    private Scanner scanner;

    /**
     * Constructor to initialise a scanner.
     */
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
        String output;
        output = HORIZONTAL_LINE;
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
    public static String printTasks(TaskList taskList) {
        String output;
        output = HORIZONTAL_LINE;
        for (int i = 0; i < taskList.getSize(); i++) {
            output += i + 1 + ". " + taskList.getTask(i) + '\n';
        }
        output += HORIZONTAL_LINE + '\n';
        return output;
    }

    /**
     * Print tasks with the matching keyword.
     *
     * @param matchingTaskList
     * @param keyword
     */
    public static String printMatchingTasks(TaskList matchingTaskList, String keyword) {
        String output;
        output = HORIZONTAL_LINE;
        output += "Below is the list of tasks with keyword - \"" + keyword + "\" :";
        for (int i = 0; i < matchingTaskList.getSize(); i++) {
            output += i + 1 + ". " + matchingTaskList.getTask(i) + '\n';
        }
        output += HORIZONTAL_LINE + '\n';
        return output;
    }


    /**
     * Prints the marked task and the corresponding message.
     *
     * @param taskList
     * @param index
     */
    public static String printMarkTask(TaskList taskList, int index) {
        String output;
        output = HORIZONTAL_LINE;
        output += "The following item has been marked as done. \n";
        output += taskList.getTask(index);
        return output;
    }


    /**
     * Prints the unmarked task and the corresponding message.
     *
     * @param taskList
     * @param index
     */
    public static String printUnmarkTask(TaskList taskList, int index) {
        String output;
        output = HORIZONTAL_LINE;
        output += "The following item has been unmarked and is now uncompleted. \n";
        output += taskList.getTask(index);
        return output;
    }

    /**
     * Prints the task to be deleted and the corresponding message.
     *
     * @param taskList
     * @param index
     */
    public static String printDeleteTask(TaskList taskList, int index) {
        String output;
        output = HORIZONTAL_LINE;
        output += "The following item has been deleted from the list. \n";
        output += taskList.getTask(index);
        return output;
    }

    /**
     * Prints the most recently added ToDo and a corresponding message.
     *
     * @param taskList
     * @return output string
     */
    public static String printAddTodo(TaskList taskList) {
        String output;
        output = HORIZONTAL_LINE;
        output += "The following ToDo has been added. \n";
        output += taskList.getLastTask() ;
        output += "You now have " + taskList.getSize() + " items in the list.";
        output += HORIZONTAL_LINE;
        return output;
    }


    /**
     * Prints the most recent added deadline and a corresponding message.
     *
     * @param taskList
     */
    public static String printAddDeadline(TaskList taskList) {
        String output;
        output = HORIZONTAL_LINE;
        output += "The following deadline has been added. \n";
        output += taskList.getLastTask() ;
        output += "You now have " + taskList.getSize() + " items in the list.";
        output += HORIZONTAL_LINE;
        return output;
    }

    /**
     * Prints the most recent added event and a corresponding message.
     *
     * @param taskList
     */
    public static String printAddEvent(TaskList taskList) {
        String output;
        output = HORIZONTAL_LINE;
        output += "The following event has been added. \n";
        output += taskList.getLastTask() ;
        output += "You now have " + taskList.getSize() + " items in the list.";
        output += HORIZONTAL_LINE;
        return output;
    }

}
