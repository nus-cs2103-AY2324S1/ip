package corgi.ui;

import java.util.Map;
import java.util.Scanner;

import corgi.commands.CommandType;

/**
 * The Ui class is responsible for managing the user interface and displaying messages, errors, and prompts.
 */
public class Ui {
    private final static String LOGO = "  ____ ___  ____   ____ ___\n"
            + " / ___/ _ \\|  _ \\ / ___|_ _|\n"
            + "| |  | | | | |_) | |  _ | |\n"
            + "| |__| |_| |  _ <| |_| || |\n"
            + " \\____\\___/|_| \\_\\\\____|___|\n";
    private final static String DIVIDER = "---------------------------------------------------------------------";
    private final static Map<String, String> errorMessage = ErrorMessage.mapper;
    private Scanner sc;
    
    /**
     * Display a message to the console.
     *
     * @param s The message to display.
     */
    private void println(String s) {
        System.out.println(s);
    }

    /**
     * Display the application logo.
     */
    public void showLogo() {
        this.println(LOGO);
    }

    /**
     * Display the introductory message.
     */
    public void showIntro() {
        this.showLogo();
        this.println("Woof! I'm Corgi!");
        this.println("So, what's your wish this time, hooman?\n");
    }

    /**
     * Read a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * Set scanner object for reading command.
     * 
     * @param sc The scanner.
     */
    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Display start line of chatbot message.
     */
    public void showStartLine() {
        this.println("\n" + DIVIDER);
    }

    /**
     * Display end line of chatbot message.
     */
    public void showEndLine() {
        this.println(DIVIDER + "\n");
    }

    /**
     * Display an error message associated with a specific exception.
     *
     * @param exception The exception class name.
     */
    public void showError(String exception) {
        this.println("Woof?! 🤬 \n");
        this.println(errorMessage.getOrDefault(exception, 
                "Oh wonderful, you've broken something. And guess what? I have \nabsolutely no idea what happened either."
                + "\n\n😇: " + exception + " occurred!"));
    }

    /**
     * Display error message associated with a specific exception.
     * Also display extra message in the exception.
     * 
     * @param exception The exception class name.
     * @param extraMsg  The extra message.
     */
    public void showError(String exception, String extraMsg) {
        this.showError(exception);
        this.println("\n😇: " + extraMsg);
    }
     
    /**
     * Display the format of a specific command type.
     * 
     * @param c The specific command type.
     */
    public void showCommandFormat(CommandType c) {
        this.println("Format: " + c.getCommandFormat());
    }

    /**
     * Display a message indicating a task has been added.
     *
     * @param type The type of task added.
     * @param taskInfo Information about the added task.
     * @param currentListSize The current size of the task list.
     */
    public void showTaskAdded(String type, String taskInfo, int currentListSize) {
        this.println("Woof, whatever. I've added this " + type + ":\n\n " 
                + taskInfo + "\n\nNow you have " + currentListSize + " tasks in the list.🐾");
    }

    /**
     * Display a message indicating tasks have been loaded from data file.
     */
    public void showTasksLoaded(int size) {
        this.println("Successfully loaded " + size + " tasks!");
    }

    /**
     * Display a exit message.
     */
    public void showExitMsg() {
        this.println("Fine! Whatever! Just go away then! See if I care! huffs");
    }

    /**
     * Display a message indicating no tasks occurred on target date.
     * 
     * @param date The target date.
     */
    public void showNoTaskOnDate(String date) {
        this.println("No tasks or events are scheduled for " + date + ".");
    }

    /**
     * Display tasks that occurred on the target date.
     * 
     * @param date The target date.
     * @param tasksOnDate The tasks occurred on the target date.
     */
    public void showTasksOnDate(String date, String tasksOnDate) {
        this.println("Here are the tasks and events happening on " + date + ":\n");
        this.println(tasksOnDate);
    }

    /**
     * Display a message indicating a task has been marked as done.
     *
     * @param taskInfo Information about the added task.
     */
    public void showTaskDone(String taskInfo) {
        this.println("Congratulations, I guess! You finally managed to do something right 🎉:\n" 
                + "\n " + taskInfo + "\n");    
    }

    /**
     * Display a message indicating a task has been marked as not done.
     *
     * @param taskInfo Information about the added task.
     */
    public void showTaskUndone(String taskInfo) {
        this.println("Oh great, you've undone something 🐕. Just like always:\n" 
        + "\n " + taskInfo + "\n");
    }

    /**
     * Display a message indicating a task has been deleted and display current list size.
     *
     * @param taskInfo Information about the added task.
     * @param currentListSize Current size of the list.
     */
    public void showTaskDeleted(String taskInfo, int currentListSize) {
        this.println("Finally got rid of that task. Took you long enough... uninterested woof\n" 
                + "\n " + taskInfo + "\n\nNow you have " + currentListSize + " tasks in the list.🐾");  
    }

    /**
     * Display a mesage indicating there is no task in the list.
     */
    public void showNoTaskInList() {
        this.println("If you haven't noticed, there's nothing here! No tasks to be found.");
    }

    /**
     * Display the task list.
     * @param taskList
     */
    public void showTaskList(String taskList) {
        this.println(taskList);
    }
}
