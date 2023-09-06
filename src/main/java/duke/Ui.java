package duke;

public class Ui {
    public Ui() {};

    public String textGenerator(String answer) {
        return answer;
    }

    /**
     * Returns a default response when user attempts to add a new Task.
     *
     * @param newT Task Object.
     * @param len Integer Object representing how many existing tasks the user has (including the newly added task)
     * @return String Object.
     */
    public String addTaskText(Task newT, int len) {
        return "Got it. I've added this task:\n" + newT.toString() + "\n"  + "Now you have " + len + " tasks in the list.";
    }

    /**
     * Returns a default response when user wants to see all the existing tasks.
     *
     * @param digit Integer Object. Index of the task in the ArrayList
     * @param newT Task Object.
     * @return String Object.
     */
    public String displayTaskInList(int digit, Task newT) {
        return digit + 1 + "." + newT.toString();
    }

    /**
     * Returns a default response when user wants to mark a particular task.
     *
     * @param newT Task Object. A task that user wants to mark.
     * @return String Object.
     */
    public String markTaskText(Task newT) {
        return "Nice! I've marked this task as done:\n" + newT.toString();
    }

    /**
     * Returns a default response when user wants to unmark a particular task.
     *
     * @param newT Task Object. A task that user wants to unmark.
     * @return String Object.
     */
    public String unmarkTaskText(Task newT) {
        return "OK, I've marked this task as not done yet:\n" + newT.toString();
    }

    /**
     * Returns a default response when user wants to delete a particular task.
     *
     * @param newT Task Object. A task that user wants to unmark.
     * @param digit Integer Object. Represents the number of user's existing tasks.
     * @return String Object.
     */
    public String deleteTaskText(Task newT, int digit) {
        return "Noted. I've removed this task:\n" + newT.toString() + "\n" + "Now you have " + digit + " tasks in the list.";
    }
}
