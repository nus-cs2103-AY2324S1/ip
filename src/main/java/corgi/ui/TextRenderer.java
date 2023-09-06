package corgi.ui;

import java.util.Map;

import corgi.commands.CommandType;

/**
 * The TextRenderer class is responsible to return message after each command.
 */
public class TextRenderer {
    private static final String LOGO = "  ____ ___  ____   ____ ___\n"
            + " / ___/ _ \\|  _ \\ / ___|_ _|\n"
            + "| |  | | | | |_) | |  _ | |\n"
            + "| |__| |_| |  _ <| |_| || |\n"
            + " \\____\\___/|_| \\_\\\\____|___|\n";
    private static final Map<String, String> errorMessage = ErrorMessage.MAPPER;

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
    public String showLogo() {
        return LOGO + "\n";
    }

    /**
     * Display the introductory message.
     */
    public String showIntro() {
        return this.showLogo() + "Woof! I'm Corgi!\n" 
                + "So, what's your wish this time, hooman?\n";
    }

    /**
     * Display an error message associated with a specific exception.
     *
     * @param exception The exception class name.
     */
    public String showError(String exception) {
        String defaultErrorMessage = "Oh wonderful, you've broken something. And guess what? "
                + "I have \nabsolutely no idea what happened either."
                + "\n\nError: " + exception + " occurred!";
        return "Woof?!\n" + errorMessage.getOrDefault(exception, defaultErrorMessage);
    }

    /**
     * Display error message associated with a specific exception.
     * Also display extra message in the exception.
     *
     * @param exception The exception class name.
     * @param extraMsg  The extra message.
     */
    public String showError(String exception, String extraMsg) {
        return this.showError(exception) + "\nError: " + extraMsg;
    }

    /**
     * Display the format of a specific command type.
     *
     * @param c The specific command type.
     */
    public String showCommandFormat(CommandType c) {
        return "Format: " + c.getCommandFormat();
    }

    /**
     * Display a message indicating a task has been added.
     *
     * @param type The type of task added.
     * @param taskInfo Information about the added task.
     * @param currentListSize The current size of the task list.
     */
    public String showTaskAdded(String type, String taskInfo, int currentListSize) {
        return "Woof, whatever. I've added this " + type + ":\n\n " 
                + taskInfo + "\n\nNow you have " + currentListSize + " tasks in the list.";
    }

    /**
     * Display a message indicating tasks have been loaded from data file.
     */
    public String showTasksLoaded(int size) {
        return "Successfully loaded " + size + " tasks!";
    }

    /**
     * Display a exit message.
     */
    public String showExitMsg() {
        return "Fine! Whatever! Just go away then! See if I care! huffs";
    }

    /**
     * Display a message indicating no tasks occurred on target date.
     *
     * @param date The target date.
     */
    public String showNoTaskOnDate(String date) {
        return "No tasks or events are scheduled for " + date + ".";
    }

    /**
     * Display tasks that occurred on the target date.
     *
     * @param date The target date.
     * @param tasksOnDate The tasks occurred on the target date.
     */
    public String showTasksOnDate(String date, String tasksOnDate) {
        return "Here are the tasks and events happening on " + date + ":\n" + tasksOnDate;
    }

    /**
     * Display a message indicating no tasks containing target keyword.
     *
     * @param keyword The target keyword.
     */
    public String showNoTaskContainsKeyword(String keyword) {
        return "No tasks containing keyword \"" + keyword + "\".";
    }

    /**
     * Display tasks that contained the target keyword.
     *
     * @param keyword The target keyword.
     * @param tasksContainKeyword The tasks containing the target keyword.
     */
    public String showTasksContainKeyword(String keyword, String tasksContainKeyword) {
        return "Here are the tasks containing keyword \"" + keyword + "\":\n" + tasksContainKeyword;
    }

    /**
     * Display a message indicating a task has been marked as done.
     *
     * @param taskInfo Information about the added task.
     */
    public String showTaskDone(String taskInfo) {
        return "Congratulations, I guess! You finally managed to do something right:\n"
                + "\n " + taskInfo + "\n";
    }

    /**
     * Display a message indicating a task has been marked as not done.
     *
     * @param taskInfo Information about the added task.
     */
    public String showTaskUndone(String taskInfo) {
        return "Oh great, you've undone something. Just like always:\n"
                + "\n " + taskInfo + "\n";
    }

    /**
     * Display a message indicating a task has been deleted and display current list size.
     *
     * @param taskInfo Information about the added task.
     * @param currentListSize Current size of the list.
     */
    public String showTaskDeleted(String taskInfo, int currentListSize) {
        return "Finally got rid of that task. Took you long enough... uninterested woof\n"
                + "\n " + taskInfo + "\n\nNow you have " + currentListSize + " tasks in the list.";
    }

    /**
     * Display a mesage indicating there is no task in the list.
     */
    public String showNoTaskInList() {
        return "If you haven't noticed, there's nothing here! No tasks to be found.";
    }

    /**
     * Display the task list.
     * @param taskList
     */
    public String showTaskList(String taskList) {
        return taskList;
    }
}
