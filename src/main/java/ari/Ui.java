package ari;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * UI class that deals with interactions with the user.
 */
public class Ui {

    public Ui() {
    }

    public static void printLine() {
        System.out.println("\t_______________________________________________________________________");
    }

    /**
     * Prints out welcome message at the start of the programme.
     */
    public void welcome() {
        printLine();
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    /**
     * Prints out bye message at the end of the programme.
     *
     * @return
     */
    public String bye() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Takes in input from the user.
     * @return user input
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public String printList(TaskList tasks) {
        printLine();
        System.out.println("\tHere are the task in your list:");
        String list = tasks.printTasks();
        printLine();

        return list;
    }

    /**
     * Prints information about the task that has been added to the TaskList object.
     * Also prints the updated number of tasks.
     * @param size updated number of tasks
     * @param task task that has been added
     * @return what Ari prints out in String
     */
    public String printAddedTask(int size, Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLine();

        return "Got it. I've added this task:" + "\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";

    }

    /**
     * Prints information about the task that has been marked as done
     * @param index task number
     * @param tasks TaskList object
     * @return what Ari prints out in String
     */
    public String printAfterMark(int index, TaskList tasks) {
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.getTasks().get(index).toString());
        printLine();

        return "Nice! I've marked this task as done:" + "\n"
                + "  " + tasks.getTasks().get(index).toString();
    }

    /**
     * Prints information about the task that has been marked as undone
     * @param index task number
     * @param tasks TaskList object
     * @return what Ari prints out in String
     */
    public String printAfterUnmark(int index, TaskList tasks) {
        printLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.getTasks().get(index).toString());
        printLine();

        return "OK, I've marked this task as not done yet:" + "\n"
                + "  " + tasks.getTasks().get(index).toString();
    }

    /**
     * Prints out information about the task that has been deleted.
     * @param size updated number of tasks
     * @param removedTask task that has been removed
     * @return what Ari prints out in String
     */
    public String printAfterDelete(int size, Task removedTask) {
        printLine();
        System.out.println("\t  " + removedTask.toString());
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLine();

        return removedTask.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";

    }

    /**
     * Prints out information about the task that contains a specified keyword.
     * @param taskList an ArrayList of task that contain the keyword
     * @return what Ari prints out in String
     */
    public String printMatchingTasks(ArrayList<Task> taskList) {
        String listOfMatchingTasks = "\tHere are the matching tasks in your list: \n";
        printLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.get(i).toString());
            listOfMatchingTasks += (i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        printLine();

        return listOfMatchingTasks;
    }
    /**
     * Prints out information about the task (deadline) that has been postponed.
     * @param postponedTask a deadline task that has been postponed
     * @return what Ari prints out in String
     */
    public String printAfterPostpone(Task postponedTask) {
        printLine();
        System.out.println("\tOk, We have postponed the following deadline");
        System.out.println("\t  " + postponedTask.toString());
        printLine();

        return "Ok, We have postponed the following deadline" + "\n"
                + "  " + postponedTask.toString();
    }

    /**
     * Prints out information about the task (event) that has been rescheduled.
     * @param rescheduledTask an event task that has been rescheduled
     * @return what Ari prints out in String
     */
    public String printAfterReschedule(Task rescheduledTask) {
        printLine();
        System.out.println("\tOk, We have rescheduled the following event");
        System.out.println("\t  " + rescheduledTask.toString());
        printLine();

        return "Ok, We have rescheduled the following event" + "\n"
                + " " + rescheduledTask.toString();
    }
}
