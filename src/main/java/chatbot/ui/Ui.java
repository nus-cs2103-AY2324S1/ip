package chatbot.ui;

import java.util.ArrayList;
import java.util.Scanner;

import chatbot.tasks.Task;
import chatbot.tasks.TaskList;

/**
 * Represents a user interface that interacts with the user
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm ChatBot.\n"
                + "What can I do for you?\n");
    }

    /**
     * Prints goodbye message
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads input
     * @return returns input
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints out the tasks in the task list.
     * @param
     */
    public void showList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.len(); i++) {
            System.out.println((i + 1) + ". " + taskList.retrieveList().get(i));
        }
    }

    /**
     * Prints out the newly added task in the task list
     * @param taskList TaskList containing the list of tasks
     */
    public void showAddedTask(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.retrieveList().get(taskList.len() - 1).toString());
        System.out.println("Now you have " + taskList.len() + " tasks in the list");
    }

    /**
     * Prints out the task that has been removed by the user
     * @param taskList TaskList containing the list of tasks
     * @param num Index of the task to be removed
     */
    public void showRemovedTask(TaskList taskList, int num) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.retrieveList().get(num).toString());
        int total = taskList.len() - 1;
        System.out.println("Now you have " + total + " tasks in the list");
    }

    /**
     * Displays the tasks with the keyword that the user inputs
     * @param taskList TaskList containing the list of tasks
     * @param input Keyword that the user inputs
     */
    public void showFindResults(TaskList taskList , String input) {
        ArrayList<Task> list = taskList.getFilteredTasks(input);

        if (list.size() == 0) {
            System.out.println("There are no tasks with that keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + list.get(i - 1).toString());
            }
        }
    }

}
