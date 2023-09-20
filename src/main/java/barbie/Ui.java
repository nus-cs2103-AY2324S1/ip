package barbie;

import java.util.ArrayList;

import barbie.exceptions.BarbieListEmptyException;
import barbie.types.Task;

/**
 * Takes care of the UI related messages that will be displayed.
 */
public class Ui {
    /**
     * Introduces the chatbot to the user.
     *
     * @param list list of Tasks that should be done today
     */
    protected static String intro(ArrayList<Task> list) {
        return ("Hi Barbie! Hi Ken!\n"
                + "I'm Barbie!\n"
                + "This is the list of things you have today!\n\n"
                + Storage.getLastList().stream().map(x -> x + "\n")
                + "\nWhat can I do for you?");

    }

    /**
     * Greets the user before ending the chat.
     */
    public static String exit() {
        return ("Bye Barbie! Bye Ken!");
    }

    /**
     * Prints that the Task has been successfully added.
     *
     * @param task Task that was added to the list
     */
    public static String taskAdded(Task task) {
        return ("Got you barbie! I've added this task to your Barbie list:\n"
                + " " + task);
    }

    /**
     * Prints that the Task has been successfully marked as done.
     *
     * @param task Task that was marked as done
     */
    public static String mark(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task + "\n");
    }

    /**
     * Prints that the Task has been successfully marked as undone.
     *
     * @param task Task that was marked as undone.
     */
    public static String unmark(Task task) {
        return ("Alright! I've marked this task as not done yet:\n"
                + task + "\n");
    }

    /**
     * Prints that the Task has been successfully deleted.
     */
    public static String del() {
        return (" Deletion success! I've deleted this task off your list.");
    }


    /**
     * Prints the Task in numerical order, or that there is no Task in the list.
     *
     * @param list list of tasks
     */
    public static String listTasks(ArrayList<Task> list) throws BarbieListEmptyException {
        if (list.size() == 0) {
            throw new BarbieListEmptyException();
        }

        String listInString = "";
        // "list" command
        for (int i = 0; i < list.size(); i++) {
            int itemNumber = i + 1;
            listInString += ("\n" + itemNumber + ". " + list.get(i));
        }
        return listInString;

    }

    /**
     * Finds tasks with the given keyword.
     * @param list list of tasks
     * @param keyword keyword to compare against
     * @return string of items listed
     */
    public static String findTasks(ArrayList<Task> list, String keyword) {
        if (list.size() == 0) {
            return ("Hmm.. your list looks empty. To add items, use the 'todo', "
                    + "'deadline' or 'party' commands!");
        }

        String toPrint = ("These are your tasks with the keyword: " + keyword);

        int itemNumber = 1;
        for (int i = 0; i < list.size(); i++) {
            String taskDesc = list.get(i).toString();
            int index = taskDesc.indexOf(keyword);
            if (index >= 0) {
                toPrint += ("\n" + itemNumber + ". " + list.get(i));
                itemNumber++;
            }
        }

        if (itemNumber == 1) {
            return ("Hmm.. There doesn't seem to be any matching tasks!");
        }

        return toPrint;
    }




}
