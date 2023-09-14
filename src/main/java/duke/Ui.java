package duke;

import duke.task.Task;
import duke.task.TaskList;

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
     * Draws a line
     *
     * @return a line
     */
    public String drawLine() {
        char horizontal_line = '\u2500';
        String line = "";

        for (int i = 0; i < 50; i++) {
            line += horizontal_line;
        }
        return line;
    }

    /**
     * Displays message of creating Task
     *
     * @param task
     */
    public String createTaskPrompt (Task task) {

        String result = "Chewie gotcha, task added:\n" + task.getStatus() + task.getTaskName();
        result += "\nChewie now find " + Duke.getListSize() + " tasks in the list";
        return result;
    }

    /**
     * Display the message of marking a task
     *
     * @param task task created
     */
    public String markPrompt(Task task) {
        String result = "Rrrruuuurrr, Chewie has marked the task.\n";
        result += task.getStatus() + task.getTaskName();

        return result;
    }

    /**
     * Displays the message of unmarking a task.
     *
     * @param task task created
     */
    public String unmarkPrompt(Task task) {
        String result = "Rrrruuuurrr, Chewie has unmarked the task.\n";
        result += task.getStatus() + task.getTaskName();
        return result;
    }

    /**
     * Display the message of deleting a task
     *
     * @param task task created
     */
    public String deletePrompt(Task task) {
        String result = "Chewie gotcha, task removed:\n" + task.getStatus() + task.getTaskName();
        result += "\nChewie now find " + (Duke.getListSize() - 1) + " tasks in the list" + "\n";
        return result;
    }

    /**
     * Display list of task
     *
     * @param taskList user's task list
     */
    public String listPrompt(TaskList taskList) {
        List<Task> list = taskList.getList();


        String result = "Chewie found your task list:\n";

        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);

            result += index + "." + task.getStatus() + task.getTaskName() + "\n";
        }
        return result;
    }

    public String findPrompt(Task[] list) {

       String result = "Chewie found these task:\n";

        for(int i = 0; i < list.length; i++) {
            int index = i + 1;
            Task task = list[i];

           result += index + "." + task.getStatus() + task.getTaskName() + "\n";
        }

        return result;
    }

    /**
     * Display the starting message of the program
     *
     */
    public String startPrompt() {
        String result = "Rrrruuuurrr, I am Chewbacca, son of Attichitcuk \nHow can Chewie help?";
        return result;
    }

    /**
     * Display ending message of program
     *
     */
    public String endPrompt() {
        return "Chewie is going home now.\nBye bye.\n";
    }

    /**
     * Display error message to user
     *
     * @param e error encountered
     */
    public String errorPrompt(Exception e) {
        return e.getMessage();
    }

    /**
     * Display wrong date format message
     *
     */
    public String wrongDateFormatPrompt() {
        return "The date format is incorrect, please use yyyy-mm-dd format";
    }


    public void setCurrInput(String text) {
        this.currInput = text;
    }

    public String readInput() {
        return currInput;
    }

}
