package duke.helper;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui class handles the user interaction and print statements for the user
 */

public class Ui {
    private Scanner scan;
    /**
     * Initialises the Ui class and sets the line string
     */
    public Ui() {
        this.scan = new Scanner(System.in);
    }

    /**
     * Prints the error message when cannot locate the storage file
     */
    public String showLoadingError() {
        return "Error! Cannot initialise new Storage";
    }

    /**
     * Reads the command input by the user
     * @return returns the command input by the user
     */

    public String readCommand() {
        String command = this.scan.nextLine();
        return command;
    }

    /**
     * Initial Greeting message by the user
     */
    public String greet() {
        String res = "";
        res += "Hello! I'm MeowBot!" + "\n";
        res += "What can I do for you \n";
        return res;
    }

    /**
     * Goodbye message to the user when closing the bot
     */
    public String bye() {
        String res = "";
        res += "Bye. Hope to see you again soon!\n";
        return res;
    }


    /**
     * Prints out the successful output when a task is added
     * @param length length of the TaskList currently
     * @param task task that is added to taslist
     */

    public String printAddTask(int length, Task task) {
        String res = "";
        res += ("MEOW got it. I've added this task:\n   " + task + "\n");
        res += ("Now you have " + length + " meow-tasks in the list. \n");
        return res;
    }

    /**
     * Prints out the successful output when a tas is marked
     * @param wantedTask task that has been marked
     */

    public String printMarkTask(Task wantedTask) {
        wantedTask.markCompleted();
        String res = "";
        res += ("Nice! I've meowrked this task as done: \n");
        res += ("   " + wantedTask + "\n");
        return res;
    }

    /**
     * Prints out the successful output when a tas is unmarked
     * @param wantedTask task that has been unmarked
     */
    public String printUnmarkTask(Task wantedTask) {
        wantedTask.markUncompleted();
        String res = "";
        res += ("Ok, get your task done soon, I'll be waiting!\n");
        res += (" " + wantedTask + '\n');
        return res;
    }

    /**
     * Prints out the message when a task is deleted succesfully
     * @param length number of tasks in TaskList currently
     * @param wantedTask Task that is to be removed from Tasklist
     */
    public String printDeleteTask(int length, Task wantedTask) {
        String res = "";
        res += ("Meow... ok, I've removed this task: \n");
        res += (" " + wantedTask + '\n');
        res += ("Now you have " + length + " meow-tasks in the list. \n");
        return res;
    }

    /**
     * Prints out the tasks stored in the TaskList
     * @param tasks TaskList to be printed
     */
    public String displayTasks(TaskList tasks) {
        String res = "";
        res += ("Meoowww here are your tasks\n");
        for (int i = 1; i < tasks.size() + 1; i++) {
            res += (i + ". " + tasks.getTask(i - 1) + '\n');
        }
        return res;
    }

    /**
     * Pirnt statement when no tasks with a given keyword is found
     * @param keyword keyword to find related tasks
     */

    public String printEmptyFind(String keyword) {
        return ("Meow :( found no tasks with " + keyword);
    }

    /**
     * Print statement when successful tasks have been found
     * @param res result of the string to be printed
     */

    public String printFindRes(String res) {
        String ans = "";
        ans += ("Meow Here are your matching tasks !" + res + '\n');
        return ans;
    }


}
