package adam;

import java.util.Scanner;

import adam.tasks.Task;

/**
 * This Class is responsible for the print statements of the program and shows the personality of the program.
 */
public class Ui {
    protected Scanner scaner = new Scanner(System.in);

    /**
     * Returns the user input string.
     *
     * @return String user input.
     */
    public String readInput() {
        return scaner.nextLine();
    }

    /**
     * Prints the welcome message at the start of the program.
     */
    public void welcome() {
        System.out.println("What's up I am Adam.Adam\n" + "so like what do you want?");
    }

    /**
     * Prints the amount of tasks you have inside your list.
     *
     * @param size Size of the list.
     */
    public void getAmount(int size) {
        System.out.println(String.format("%d adam.tasks in this list, stop procrasinating them!!!", size));
    }

    /**
     * Prints out the delete message when you delete a Task.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public void delete(Task curr, int size) {
        System.out.println("I have removed the Tasks.Task, so just make up your mind next time:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    /**
     * Prints out a message whenever you add a todo Task to your list.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public void addTodo(Task curr, int size) {
        System.out.println("I added this todo to the list of endless work you have:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    /**
     * Prints out a message whenever you add a event Task to your list.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public void addEvent(Task curr, int size) {
        System.out.println("I added this event to your list, congrats on having a life outside of work:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    /**
     * Prints out a message whenever you add a deadline Task to your list.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public void addDeadline(Task curr, int size) {
        System.out.println("I have added this deadline to the list, good " +
                "luck on remembering it one day before deadline:");
        System.out.println(curr.toString());
        getAmount(size);
    }

    /**
     * Lists out all the task in your list.
     */
    public void list() {
        System.out.println("Here are the amount of hard labor you have in your lists:");
    }

    /**
     * Prints a goodbye message when you end the program.
     */
    public void bye() {
        System.out.println("Bye. Hope we don't see each other too often");
    }

    /**
     * Prints out a message to indicate that you have completed a Task.
     */
    public void mark() {
        System.out.println("Congrats on getting one step closer to achieving true happines," +
                " I have marked this task as complete");
    }

    /**
     * Prints out a message to indicate that you have uncompleted a Task.
     */
    public void unmark() {
        System.out.println("turns out you still got more work to do, I have unmarked it");
    }

    /**
     * Prints out error messages that has been thrown inside the program.
     *
     * @param message Message of the specific error.
     */
    public void displayError(String message) {
        System.out.println(message);
    }
}
