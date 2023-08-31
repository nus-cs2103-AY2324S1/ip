package ruiz;

import ruiz.task.Task;

import java.util.ArrayList;

/**
 * Deals with the interactions with the user.
 */
public class Ui {
    /**
     * This method prints the greeting message of the bot.
     */
    public void greet() {
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Ruiz\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);
    }

    /**
     * This method prints the goodbye message of the bot.
     */
    public void bye() {
        String bye = "____________________________________________________________\n" +
                "Bye! Comeback soon!\n" +
                "____________________________________________________________";
        System.out.println(bye);
    }

    /**
     * This method prints out the list of tasks currently
     */
    public void getTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void unableToSaveTask() {
        System.out.println("____________________________________________________________\n" +
                "Unable to save task" +
                "____________________________________________________________");
    }

    public void wrongFormat() {
        System.out.println("____________________________________________________________\n" +
                "Please input your date and time in the correct format: yyyy-MM-dd HHmm\n" +
                "____________________________________________________________");
    }

    public String botErrorMsg() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public void unableToLoadFile() {
        System.out.println("____________________________________________________________\n" +
                "There is no pre-existing list\n" +
                "____________________________________________________________");
    }

    /**
     * Prints an acknowledgement message that a new task has been added.
     * @param task task that is added.
     * @param taskListSize size of the task list after this task is added.
     */
    public void addedNewTaskMsg(Task task, int taskListSize) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                task +
                "\nNow you have " + taskListSize + " this.taskList in the list.\n" +
                "____________________________________________________________"
        );
    }

    /**
     * Prints an acknowledgement message that the task has been deleted.
     * @param task task that is deleted.
     * @param taskListSize size of the task list after the task is deleted.
     */
    public void deletedTask(Task task, int taskListSize) {
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + taskListSize + " this.taskList in the list.\n" +
                "____________________________________________________________");
    }

    /**
     * Prints an acknowledgement message that the task has been unmarked.
     * @param task task that is marked.
     */
    public void unmarkTask(Task task) {
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet\n" +
                task +
                "\n" +
                "____________________________________________________________\n");
    }

    /**
     * Prints an acknowledgement message that the task has been marked.
     * @param task
     */
    public void markTask(Task task) {
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done\n" +
                task +
                "\n" +
                "____________________________________________________________");
    }
}
