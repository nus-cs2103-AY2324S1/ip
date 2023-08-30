package duke;

import java.util.Scanner;

/**
 * Ui class mainly used for printing.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * To print the message of Exception objects
     * @param exceptionMessage the message from the Exception objects
     */
    public void printException(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    /**
     * To list all the tasks present in the list.
     * @param lst the TaskList we want to be iterating through
     */
    public void listMessage(TaskList lst) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.println(String.valueOf(i + 1) + "." + task.toString());
        }
    }
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }
    public void closeScanner() {
        this.sc.close();
    }

    /**
     * To print confirmation message when something is unmarked
     * @param index the index of the task we want to undo
     * @param lst TaskList containing all the tasks
     */
    public void uncompletedMessage(int index, TaskList lst) {
        Task tsk = lst.get(index);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(tsk.toString());
    }

    /**
     * To print confirmation message when something is marked
     * @param index the index of the task completed
     * @param lst TaskList containing all the tasks
     */
    public void completedMessage(int index, TaskList lst) {
        Task tsk = lst.get(index);
        System.out.printf("Nice! I've marked this task as done: \n");
        System.out.println(tsk.toString());
    }

    /**
     * To print confirmation message once a Task has been deleted
     * @param index index of task deleted
     * @param lst TaskList storing all the tasks
     */
    public void deletedMessage(int index, TaskList lst) {
        Task tsk = lst.get(index);
        System.out.println(tsk.removed());
    }

    /**
     * To print confirmation message once a Task has been deleted
     * @param tsk The task added to the list
     * @param lst TaskList containing all the tasks
     */
    public void addedMessage(Task tsk, TaskList lst) {
        System.out.println(tsk.confirmation(lst.size()));
    }
    public String getCommand() {
        return this.sc.nextLine();
    }
    public String byeGreetings() {
        return "Bye. Hope to see you again soon!";
    }

    public String name = " ____    __        __       \n"
            + "|  _ \\   | |  ____ | | __\n"
            + "| | | |  | | |     | |/ /\n"
            + "| |_| |  | | |     |   < \n"
            + "|____/   ___  ____ |_|\\_\\\n";
    public String greet() {
        return "Hello from \n " + this.name + "What can I do for you? ";
    }

}
