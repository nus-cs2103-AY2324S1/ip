package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A Class that handle the User Interaction
 *
 *
 */
public class Ui {
    private final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    /**
     * A method that print the message with the line.
     * @param str the message in string
     */
    public void printFinal(String str) {
        System.out.println(this.HORIZONTAL_LINE + str + this.HORIZONTAL_LINE);
    }

    /**
     * A method that print greeting message
     */
    public void printGreet() {
        printFinal("     Hello! I'm Siri\n" +
                "     What can I do for you?\n");
    }

    /**
     * A method that print goodbye message.
     */
    public void printBye() {
        printFinal("     Bye. Hope to see you again soon!\n");
    }

    /**
     * Print the message when adding a task.
     *
     * @param newTask the newly added task
     * @param numberOfTask The number of tasks in the TaskList
     */
    public void printAddTask(Task newTask, int numberOfTask) {
        printFinal("     Got it. I've added this duke.task:\n" +
                "      " + newTask + "\n" +
                "     Now you have " + numberOfTask + " tasks in the list." + "\n");
    }

    /**
     * Print the message when deleting a task.
     *
     * @param deletedTask the deleted task
     * @param numberOfTask the number of tasks in the TaskList
     */
    public void printDeleteTask(Task deletedTask, int numberOfTask) {
        printFinal("     Noted. I've removed this duke.task:" + "\n" +
                "       " + deletedTask + "\n" +
                "     Now you have " + numberOfTask + " tasks in the list." + "\n");
    }

    /**
     * Print all the task in the taskList.
     *
     * @param taskList the taskList that going to be printed.
     */
    public void printTaskList(TaskList taskList) {
        String items = "";

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                break;
            }
            items += "     " + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }

        printFinal("     Here are the tasks in your list:\n" + items);
    }

    /**
     * Print the message when marking a task.
     *
     * @param task The marked task.
     */
    public void printMark(Task task) {
        printFinal("     Nice! I've marked this duke.task as done:" + "\n" +
                "       " + task + "\n");

    }

    /**
     * Print the message when unmarking a task.
     *
     * @param task the unmark task
     */
    public void printUnmark(Task task) {
        printFinal("     OK, I've marked this duke.task as not done yet:" + "\n" +
                "       " + task + "\n");
    }

    /**
     * Print the error for DukeException
     *
     * @param e the error message.
     */
    public void printError(String e) {
        printFinal("       " + e + "\n");
    }

}
