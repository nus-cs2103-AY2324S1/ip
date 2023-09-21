package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the input and output of the program
 *
 * @author Lian Zhi Xuan
 */
public class Ui {

    //singleton
    public static Ui instance = new Ui();

    private String currInput = "";

    /**
     * Displays message of creating Task.
     *
     * @param task task to crete prompt form.
     */
    public String createTaskPrompt (Task task) {

        String result = "Chewie gotcha, task added:\n" + task.getStatus() + task.getTaskName();
        result += "\nChewie now find " + Duke.getListSize() + " tasks in the list";
        return result;
    }

    /**
     * Displays message of assigning tag to task.
     *
     * @param task task to crete prompt form.
     */
    public String assignTagPrompt(Task task) {
        String result = "Chewie gotcha, tag added to task :\n";
        result += task.getStatus() + task.getTaskName() + " " + tagPrompt(task);

        return result;
    }

    /**
     * Displays message of removing tag from task.
     *
     * @param task task to crete prompt form.
     */
    public String removeTagPrompt(Task task) {
        String result = "Chewie gotcha, tag removed from task :\n";
        result += task.getStatus() + task.getTaskName() + " " + tagPrompt(task);

        return result;
    }

    /**
     * Displays list of tasks with the tag.
     *
     * @param tasks tasks to crete prompt form.
     */
    public String findTaskWithTagPrompt(Task[] tasks) {
         String result = "Chewie found these tasks with the tag :\n";

         for (int i = 0; i < tasks.length; i++) {
             Task t = tasks[i];
             result += (i+1) + ". " + t.getStatus() + t.getTaskName() + " " + tagPrompt(t) + "\n";
         }
        return result;
    }

    /**
     * Displays message of assigning tag to task.
     *
     * @param task task to crete prompt form.
     */
    private String tagPrompt(Task task) {
        String result = "( ";
        ArrayList<String> list = task.getTags();

        for (int i = 0; i < list.size(); i++) {
            result += "#" + list.get(i) + " ";
        }
        return result + ")";
    }
    /**
     * Displays the message of marking a task.
     *
     * @param task task created.
     */
    public String markPrompt(Task task) {
        String result = "Rrrruuuurrr, Chewie has marked the task.\n";
        result += task.getStatus() + task.getTaskName() + " " + tagPrompt(task);

        return result;
    }

    /**
     * Displays the message of unmarking a task.
     *
     * @param task task created.
     */
    public String unmarkPrompt(Task task) {
        String result = "Rrrruuuurrr, Chewie has unmarked the task.\n";
        result += task.getStatus() + task.getTaskName() + " " + tagPrompt(task);
        return result;
    }

    /**
     * Displays the message of deleting a task.
     *
     * @param task task created.
     */
    public String deletePrompt(Task task) {
        String result = "Chewie gotcha, task removed:\n" + task.getStatus()
                + task.getTaskName() + " " + tagPrompt(task);

        result += "\nChewie now find " + (Duke.getListSize() - 1) + " tasks in the list" + "\n";
        return result;
    }

    /**
     * Displays list of task.
     *
     * @param taskList user's task list.
     */
    public String listPrompt(TaskList taskList) {
        List<Task> list = taskList.getList();

        String result = "Chewie found your task list:\n";

        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);

            result += index + "." + task.getStatus() + task.getTaskName() + " " + tagPrompt(task) + "\n";
        }
        return result;
    }

    /**
     * Displays list of tasks with selected keyword.
     *
     * @param list tasks to crete prompt from.
     */
    public String findPrompt(Task[] list) {
        if (list.length == 0) {
            return "Chewie found nothing. ";
        }

       String result = "Chewie found these task:\n";

        for(int i = 0; i < list.length; i++) {
            int index = i + 1;
            Task task = list[i];

            result += index + "." + task.getStatus() + task.getTaskName() + " " + tagPrompt(task) + "\n";
        }

        return result;
    }

    /**
     * Displays the starting message of the program.
     *
     */
    public String startPrompt() {
        return "Rrrruuuurrr, I am Chewbacca, son of Attichitcuk \nHow can Chewie help?";
    }

    /**
     * Displays ending message of program.
     *
     */
    public String endPrompt() {
        return "Chewie is going home now.\nBye bye.\n";
    }

    /**
     * Displays error message to user.
     *
     * @param e error encountered
     */
    public String errorPrompt(Exception e) {
        return e.getMessage();
    }

    /**
     * Displays wrong date format message.
     *
     */
    public String wrongDateFormatPrompt() {
        return "The date format is incorrect, please use yyyy-mm-dd format";
    }

    /**
     * Records the input into Ui instance.
     *
     * @param text input from user.
     */
    public void setCurrInput(String text) {
        this.currInput = text;
    }

    public String getCurrInput() {
        return currInput;
    }

}
