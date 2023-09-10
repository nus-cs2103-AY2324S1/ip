package duke.main;

/**
 * User interface.
 */
public class Ui {
    /**
     * Returns reply format for list out tasks.
     * @param index
     * @param task
     * @return
     */
    public String listOutTasksString(int index, String task) {
        return String.format("%d. %s\n", index, task);
    }

    /**
     * Returns reply format for exit.
     * @return
     */
    public String exitString() {
        return "  Bye~ Hope to see you again soon! >w<";
    }

    /**
     * Returns reply format for mark.
     * @param task
     * @return
     */
    public String markString(String task) {
        return String.format("  Nice! I've marked this task as done:\n  %s", task);
    }

    /**
     * Returns reply format for unmark.
     * @param task
     * @return
     */
    public String unmarkString(String task) {
        return String.format("  Ok, I've marked this task as not done yet:\n  %s", task);
    }

    /**
     * Returns reply format for delete.
     * @param removedTask
     * @param taskSize
     * @return
     */
    public String deleteString(String removedTask, int taskSize) {
        String removeNofi = String.format("  Noted. I've removed this task:");
        String removedDetail = String.format("  %s", removedTask);
        String taskCount = String.format("  Now you have %d task(s) in the list.", taskSize);
        return String.format("%s\n%s\n%s", removeNofi, removedDetail, taskCount);
    }

    /**
     * Returns reply format for add task.
     * @param addedTask
     * @param taskSize
     * @return
     */
    public String addTaskString(String addedTask, int taskSize) {
        String addNofi = String.format("  Got it. I've added this task:");
        String addedDetail = String.format("  %s", addedTask);
        String taskCount = String.format("  Now you have %d task(s) in the list.", taskSize);
        return String.format("%s\n%s\n%s", addNofi, addedDetail, taskCount);
    }

    /**
     * Returns reply format for find.
     * @param matchingTasks
     * @return
     */
    public String findString(String matchingTasks) {
        String findNofi = String.format("  Here are the matching tasks in your list:");
        return String.format("%s\n%s", findNofi, matchingTasks);
    }

}
