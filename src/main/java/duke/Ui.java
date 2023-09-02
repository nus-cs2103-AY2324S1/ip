package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the input and output of the program
 *
 * @author Lian Zhi Xuan
 */
public class Ui {

    //singleton
    public static Ui ui = new Ui();

    Scanner scanner = new Scanner(System.in);

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
     * Draws logo of DUKE
     *
     */
    public void logo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
    }

    /**
     * Displays message of creating Task
     *
     * @param task
     */
    public void createTaskPrompt (Task task) {

        System.out.println(drawLine());
        System.out.println("Chewie gotcha, task added:\n" + task.status() + task.taskName());
        System.out.println("Chewie now find " + Duke.listSize() + " tasks in the list" + "\n");
        System.out.println(drawLine());
    }

    /**
     * Display the message of marking a task
     *
     * @param task task created
     */
    public void markPrompt(Task task) {
        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, Chewie has marked the task.");
        System.out.println(task.status() + task.taskName());
        System.out.println("\n" + drawLine());
    }

    /**
     * Displays the message of unmarking a task.
     *
     * @param task task created
     */
    public void unmarkPrompt(Task task) {
        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, Chewie has unmarked the task.");
        System.out.println(task.status() + task.taskName());
        System.out.println("\n" + drawLine());
    }

    /**
     * Display the message of deleting a task
     *
     * @param task task created
     */
    public void deletePrompt(Task task) {
        System.out.println(drawLine());
        System.out.println("Chewie gotcha, task removed:\n" + task.status() + task.taskName());
        System.out.println("Chewie now find " + (Duke.listSize() - 1) + " tasks in the list" + "\n");
        System.out.println(drawLine());
    }

    /**
     * Display list of task
     *
     * @param taskList user's task list
     */
    public void listPrompt(TaskList taskList) {
        List<Task> list = taskList.list();

        System.out.println(drawLine());
        System.out.println("Chewie found your task list:");

        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);

            System.out.println(index + "." + task.status() + task.taskName());
        }

        System.out.println("\n" + drawLine());
    }

    /**
     * Display the starting message of the program
     *
     */
    public void startPrompt() {
        logo();
        
        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, I am Chewbacca, son of Attichitcuk");
        System.out.println("How can Chewie help?\n");
        System.out.println(drawLine());
    }

    /**
     * Display ending message of program
     *
     */
    public void endPrompt() {
        System.out.println(drawLine());
        System.out.println("Chewie is going home now.\nBye bye.\n");
        System.out.println(drawLine());
    }

    /**
     * Display error message to user
     *
     * @param e error encountered
     */
    public void errorPrompt(Exception e) {
        System.out.println(drawLine());
        System.out.println(e.getMessage());
        System.out.println("\n" + drawLine());
    }

    /**
     * Display wrong date format message
     *
     */
    public void wrongDateFormatPrompt() {
        System.out.println(drawLine());
        System.out.println("The date format is incorrect, please use yyyy-mm-dd format");
        System.out.println("\n" + drawLine());
    }

    /**
     * Read input from user
     *
     * @return input as String
     */
    public String readInput() {
        return scanner.nextLine();
    }
}
