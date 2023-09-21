package nexus.components;

import nexus.task.Task;
import nexus.task.TaskList;

/**
 * Display text for user interaction.
 */
public class Ui {
    /**
     * Show current task.
     *
     * @param task Task.
     * @return String.
     */
    private String showTask(Task task) {
        return task.toString();
    }

    /**
     * Show all tasks.
     *
     * @param list TaskList.
     * @return String.
     */
    public String showList(TaskList list) {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(i + 1).append(".").append(list.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Show marked task.
     *
     * @param task Task.
     * @return String.
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + this.showTask(task);
    }

    /**
     * Show unmarked task.
     *
     * @param task Task.
     * @return String.
     */
    public String printUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + this.showTask(task);
    }

    /**
     * Show deleted task and number of remaining tasks.
     *
     * @param task Task.
     * @param list TaskList.
     * @return String.
     */
    public String showDelete(Task task, TaskList list) {
        return "Noted. I've removed this task:\n"
                + this.showTask(task)
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Show added task and new number of tasks.
     *
     * @param task Task.
     * @param list TaskList.
     * @return String.
     */
    public String showAdd(Task task, TaskList list) {
        return "Noted. I've added this task:\n"
                + this.showTask(task)
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Show results matching the query.
     *
     * @param keyword Search query.
     * @param list TaskList.
     * @return String.
     */
    public String showFind(String keyword, TaskList list) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getDescription().contains(keyword)) {
                stringBuilder.append(count).append(".").append(list.get(i)).append("\n");
                count++;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Show welcome message.
     *
     * @return String.
     */
    public String showWelcome() {
        return "Hello! I'm NEXUS!\nWhat can I do for you?";
    }

    /**
     * Show goodbye message.
     *
     * @return String.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}
