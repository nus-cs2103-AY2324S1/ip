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
     */
    private void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Show all tasks.
     *
     * @param list TaskList.
     */
    public void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1);
            System.out.println("." + list.get(i));
        }
    }

    /**
     * Show marked task.
     *
     * @param task Task.
     */
    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        this.printTask(task);
    }

    /**
     * Show unmarked task.
     *
     * @param task Task.
     */
    public void printUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        this.printTask(task);
    }

    /**
     * Show deleted task and number of remaining tasks.
     *
     * @param task Task.
     * @param list TaskList.
     */
    public void printDelete(Task task, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        this.printTask(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Show added task and new number of tasks.
     *
     * @param task Task.
     * @param list TaskList.
     */
    public void printAdd(Task task, TaskList list) {
        System.out.println("Got it. I've added this task:");
        this.printTask(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Show results matching the query.
     *
     * @param keyword Search query.
     * @param list TaskList.
     */
    public void printFind(String keyword, TaskList list) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.print(count);
                System.out.println("." + list.get(i));
                count++;
            }
        }
    }

    /**
     * Show welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
    }

    /**
     * Show goodbye message.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
