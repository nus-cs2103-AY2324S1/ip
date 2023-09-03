import types.Task;

import java.util.ArrayList;

/**
 * Takes care of any of the UI related messages that will be displayed.
 */
public class Ui {

    private static final String line = "______________________________\n";

    /**
     * Introduces the chatbot to the user.
     *
     * @param list list of Tasks that should be done today
     */
    protected static void intro(ArrayList<Task> list) {
        String logo = " ____             _\n"
                + "|  _ \\           | |\n"
                + "| |_| |_____,_ ,_| |,___  _  ___\n"
                + "|  _ /|  _  | ` _|  __\\ \\ |/ _  \\\n"
                + "| |_| | |_| | |  | |__/ /| |  ___/\n"
                + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
        System.out.println(line
                + "Hi Barbie! Hi Ken!\n"
                + "\nI'm\n"
                + logo
                + "\n\nThis is the list of things you have today!");
        list.forEach(System.out::println);
        System.out.println("\nWhat can I do for you?\n" + line);
        System.out.println("[you]:");

    }

    /**
     * Greets the user before ending the chat.
     */
    protected static void exit() {
        System.out.println("Bye Barbie! Bye Ken!");
    }

    /**
     * Prints that the Task has been successfully added.
     *
     * @param task Task that was added to the list
     */
    protected static void taskAdded(Task task) {
        System.out.println("\tGot you barbie! I've added this task to your Barbie list:\n"
                + "\t " + task);
    }

    /**
     * Prints that the Task has been successfully marked as done.
     *
     * @param task Task that was marked as done
     */
    protected static void mark(Task task) {
        System.out.println("\t Nice! I've marked this task as done:\n"
                + "\t " + task + "\n"
                + "\t" + line);
    }

    /**
     * Prints that the Task has been successfully marked as undone.
     *
     * @param task Task that was marked as undone.
     */
    protected static void unmark(Task task) {
        System.out.println("\t Alright! I've marked this task as not done yet:\n"
                + "\t " + task + "\n"
                + "\t" + line);
    }

    /**
     * Prints that the Task has been successfully deleted.
     */
    protected static void del() {
        System.out.println("\t Deletion success! I've deleted this task off your list.");
    }

    /**
     * Prints a "[barbie]:" to signal that the chatbot is the one talking.
     */
    protected static void barbie() {
        System.out.println("\t" + line
                + "\t [barbie]:\n");
    }

    /**
     * Prints the Task in numerical order, or that there is no Task in the list.
     *
     * @param list list of tasks
     * @param indexNumber size of list (for optimised performance)
     */
    protected static void listTasks(ArrayList<Task> list,int indexNumber) {
        if (indexNumber == 0) {
            System.out.println("Hmm.. your list looks empty. To add items, use the 'todo', 'deadline' or 'party' commands!");
        }
        // "list" command
        for (int i = 0; i < indexNumber; i++) {
            int itemNumber = i + 1;
            System.out.println("\t" + itemNumber + ". " + list.get(i));
        }
    }




}
