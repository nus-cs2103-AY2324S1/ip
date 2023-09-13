package adam;

import adam.tasks.Task;

import java.util.Scanner;

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
    public String welcome() {
         return "What's up I am Adam\n" + "so like what do you want?";
    }

    /**
     * Prints the amount of tasks you have inside your list.
     *
     * @param size Size of the list.
     */
    public String getAmount(int size) {
        return String.format("%d tasks in this list, stop procrasinating them!!!", size);
    }

    /**
     * Prints out the delete message when you delete a Task.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public String delete(Task curr, int size) {
        return "I have removed the Task, so just make up your mind next time:\n" + curr.toString() +
                "\n" + getAmount(size);
    }

    /**
     * Prints out a message whenever you add a todo Task to your list.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public String addTodo(Task curr, int size) {
       return "I added this todo to the list of endless work you have:\n" + curr.toString() +
               "\n" + getAmount(size);
    }

    /**
     * Prints out a message whenever you add a event Task to your list.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public String addEvent(Task curr, int size) {
        return "I added this event to your list, congrats on having a life outside of work:" + curr.toString() +
                "\n" + getAmount(size);
    }

    /**
     * Prints out a message whenever you add a deadline Task to your list.
     *
     * @param curr Current Task.
     * @param size Size of the list.
     */
    public String addDeadline(Task curr, int size) {
        return "I have added this deadline to the list, good " +
                "luck on remembering it one day before deadline:\n" + curr.toString() + "\n" + getAmount(size);
    }

    /**
     * Lists out all the task in your list.
     */
    public String list() {
        return "Here are the amount of hard labor you have in your lists:";
    }

    /**
     * Prints a goodbye message when you end the program.
     */
    public String bye() {
        return "Bye. Hope we don't see each other too often";
    }

    /**
     * Prints out a message to indicate that you have completed a Task.
     */
    public String mark() {
        return "Congrats on getting one step closer to achieving true happines," +
                " I have marked this task as complete";
    }

    /**
     * Prints out a message to indicate that you have uncompleted a Task.
     */
    public String unmark() {
        return "turns out you still got more work to do, I have unmarked it";
    }

    /**
     * Prints out error messages that has been thrown inside the program.
     *
     * @param message Message of the specific error.
     */
    public String displayError(String message) {
        return message;
    }

    /**
     * Prints out whenever the find method couldnt find a match.
     */
    public String apologize() {
        return "I couldn't find anything are you sure its here?";
    }

    /**
     * Prints when you find a match using the find method.
     */
    public String search() {
        return "Here are some of the tasks I found:";
    }
}
