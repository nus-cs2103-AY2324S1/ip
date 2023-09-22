package duke;

import java.time.LocalDate;

public class UiGUI {
    public UiGUI() {
    }

    /**
     * Prints that a bad date/time was found
     */
    public static String badDateLoaded() {
        return "Invalid date/time format encountered";
    }

    /**
     * Prints the error
     */
    public static String Error() {
        return ("An error occurred");
    }

    /**
     * Prints the error with the exception message
     *
     * @param e the Exception that was thrown
     */
    public static String Error(Exception e) {
        return ("An error occurred" + e.getMessage());
    }

    private String printLines() {
        return ("");
    }

    /**
     * Tells the user that he has entered in valid query for the specified type
     *
     * @param taskType string, the type of task
     */
    public String showError(String taskType) {
        return this.printLines() + "☹ OOPS!!! The description of a " + taskType + " cannot be empty." + this.printLines();
    }

    /**
     * Tells the user that he has entered in valid query for the specified type
     *
     * @param info string, the type of task
     */
    public String showExError(String info) {
        return info;
    }

    /**
     * Tells the user the number of tasks left
     *
     * @param i the number of tasks left
     */
    public String showNumTasks(int i) {
        return this.printLines() + "Now you have " + i + " tasks in the list." + this.printLines();
    }

    /**
     * Sends the user a welcome message
     */
    public String hello() {
        String logo = "___________  __________  __________  ||   \n" + "|         | |         | |         |  ||      \n" + "-----------  ---------- ----------   ||  \n" + "    ||         ||          ||        ||       \n" + "    ||         ||          ||        ||           \n" + "    ||         ||          ||        ||           \n" + "    ||         ||          ||        ||       \n" + "    ||      __________  __________   ||      \n" + "    ||      |         | |         |  ______ \n" + "    ||      ----------  ----------   ______    \n";
        return this.printLines() + ("Hello! I'm \n" + logo) + "What can I do for you?" + this.printLines();
    }

    /**
     * says Goodbye to the user
     */
    public String goodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Notifies the user the tasks in list and the number of tasks
     *
     * @param tasks the TaskList of tasks
     */
    public String tasksInList(TaskList tasks) {
        String Total = this.printLines();
        Total += "Here are the tasks in your list:" + "\n";
        int i = 1;
        for (Task t : tasks.getList()) {
            Total += (i + "." + t.toString()) + "\n";
            i++;
        }
        Total += this.printLines();
        return Total;
    }

    /**
     * Notifies the user that a task has been marked as done
     *
     * @param item the item marked as done
     */
    public String taskDone(Task item) {
        return this.printLines() + "Nice! I've marked this task as done:" + item.toString() + this.printLines();
    }

    /**
     * Notifies the user that a task has been unmarked
     *
     * @param item the item that has been unmarked
     */
    public String taskUndone(Task item) {
        return this.printLines() + ("OK, I've marked this task as not done yet:") + (item.toString()) + this.printLines();
    }

    /**
     * Notifies the user that a task has been added
     *
     * @param item  the item that has been added
     * @param tasks the TaskList containing the tasks
     */
    public String taskAdd(Task item, TaskList tasks) {
        return this.printLines() + ("Got it. I've added this task:") + (item.toString()) + ("Now you have " + tasks.getList().size() + " tasks in the list.") + this.printLines();
    }

    /**
     * Notifies the user that a task has been deleted
     *
     * @param item  the item that has been deleted
     * @param tasks the TaskList
     */
    public String taskDelete(Task item, TaskList tasks) {
        return this.printLines() + ("Noted. I've removed this task:") + "\n" + (item.toString()) + ("Now you have " + tasks.getList().size() + " tasks in the list.") + this.printLines();
    }

    /**
     * Prints that an unrecognised command was found
     */
    public String unrecognisedCommand() {
        return ("____________________________________________________________") + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + ("____________________________________________________________");
    }


    /**
     * Prints the tasks that match the query string
     *
     * @param tasks The TaskList
     * @param query the String the user wishes to query with
     */
    public String printMatchingTasks(TaskList tasks, String query) {
        String total = this.printLines();
        total += ("Here are the matching tasks in your list:") + "\n";
        int i = 1;
        for (Task t : tasks.getList()) {
            if (t.toString().contains(query)) {
                total += (i + "." + t) + "\n";
                i++;
            }
        }
        total += this.printLines();
        return total;
    }

    /**
     * Reminds the user of the deeadlines coming up
     *
     * @param tasks        The TaskList
     * @param numberOfDays the number of days until the deadline is due
     */
    public String remind(TaskList tasks, int numberOfDays) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the deadline threshold date
        LocalDate thresholdDate = currentDate.plusDays(numberOfDays);

        // Create a StringBuilder to store the reminders
        String reminders = "";
        reminders += ("Here are the deadlines in the next ") + (numberOfDays) + (" days:\n");

        int i = 1;
        for (Task task : tasks.getList()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (!deadline.isDone()) {
                    LocalDate taskDate = deadline.getDate();
                    if (taskDate != null && taskDate.isBefore(thresholdDate)) {
                        reminders += (i) + (". ") + (task) + ("\n");
                        i++;
                    }
                }
            }
        }

        if (i == 1) {
            return "No upcoming deadlines in the next " + numberOfDays + " days.";
        }
        return reminders;
    }

}
