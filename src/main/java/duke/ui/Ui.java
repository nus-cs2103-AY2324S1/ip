package duke.ui;

import duke.Duplicate.Duplicate;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A Class that handle the User Interaction
 *
 *
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    /**
     * A method that print the message with the line.
     * @param str the message in string
     */
    public void printFinal(String str) {
        System.out.println(this.HORIZONTAL_LINE + str + this.HORIZONTAL_LINE);
    }

    /**
     * A method that print greeting message
     *
     * @return Greeting statement.
     */
    public String printGreet() {
        printFinal("     Hello! I'm Siri\n"
                + "     What can I do for you?\n");
        return "     Hello! I'm Siri\n"
                + "     What can I do for you?\n";
    }

    /**
     * A method that print goodbye message.
     *
     * @return Goodbye message
     */
    public String printBye() {
        printFinal("     Bye. Hope to see you again soon!\n");
        return "     Bye. Hope to see you again soon!\n";
    }

    /**
     * Print the message when adding a task.
     *
     * @param newTask the newly added task
     * @param numberOfTask The number of tasks in the TaskList
     * @return Task added statement.
     */
    public String printAddTask(Task newTask, int numberOfTask) {
        printFinal("     Got it. I've added this task:\n"
                + "      " + newTask + "\n"
                + "     Now you have " + numberOfTask + " tasks in the list." + "\n");

        return "     Got it. I've added this task:\n"
                + "      " + newTask + "\n"
                + "     Now you have " + numberOfTask + " tasks in the list." + "\n";
    }

    /**
     * Print the message when deleting a task.
     *
     * @param deletedTask the deleted task
     * @param numberOfTask the number of tasks in the TaskList
     * @return delete message.
     */
    public String printDeleteTask(Task deletedTask, int numberOfTask) {
        printFinal("     Noted. I've removed this task:" + "\n"
                + "       " + deletedTask + "\n"
                + "     Now you have " + numberOfTask + " tasks in the list." + "\n");

        return "     Noted. I've removed this task:" + "\n"
                + "       " + deletedTask + "\n"
                + "     Now you have " + numberOfTask + " tasks in the list." + "\n";
    }

    /**
     * Print all the task in the taskList.
     *
     * @param taskList the taskList that going to be printed.
     * @return all the task in the taskList.
     */
    public String printTaskList(TaskList taskList) {
        String items = "";

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                break;
            }
            items += "     " + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }

        printFinal("     Here are the tasks in your list:\n" + items);
        return "     Here are the tasks in your list:\n" + items;
    }

    /**
     * Print the message when marking a task.
     *
     * @param task The marked task.
     * @return Marked task message.
     */
    public String printMark(Task task) {
        printFinal("     Nice! I've marked this task as done:" + "\n"
                + "       " + task + "\n");

        return "     Nice! I've marked this task as done:" + "\n"
                + "       " + task + "\n";
    }

    /**
     * Print the message when unmarking a task.
     *
     * @param task the unmark task
     * @return unmarked task message.
     */
    public String printUnmark(Task task) {
        printFinal("     OK, I've marked this task as not done yet:" + "\n"
                + "       " + task + "\n");

        return "     OK, I've marked this task as not done yet:" + "\n"
                + "       " + task + "\n";
    }

    /**
     * Print the error for DukeException
     *
     * @param e the error message.
     */
    public void printError(String e) {
        printFinal("       " + e + "\n");
    }

    /**
     * Print message after changing duplicate mode.
     *
     * @return the message after changing duplicates mode.
     */
    public String printUpdateDuplicatesMode() {
        printFinal("     OK, update duplicates mode.\n");

        return "     OK, update duplicates mode.\n";
    }

    /**
     * Print the current DuplicatesMode
     * @param duplicate the duplicate object
     * @return the current duplicates mode.
     */
    public String printDuplicatesMode(Duplicate duplicate) {
        printFinal("     The Duplicate Mode is " + duplicate.getMode());

        return "     The Duplicate Mode is " + duplicate.getMode();
    }

}
