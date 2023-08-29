package duke.taskmanagement;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public Scanner sc = new Scanner(System.in);

    /**
     * Constructor to create a Ui object.
     */
    public Ui() {
    }

    /**
     * Returns the String that the scanner read in from user inputs.
     * @return User input as string.
     */
    public String readInCmd() {
            return sc.nextLine();
    }

    /**
     * To print the greeting to user when running this program.
     */
    public void greet(){
        System.out.println("Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n");
    }

    /**
     * To say goodbye to the user when the program ends.
     */
    public void bye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * To print every element in the list.
     * @param size The size of the list you wish to print.
     * @param ls The List<Task> you wish to print.
     */
    public void printList(int size, List<Task> ls){
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < size;i++) {
            int j = i + 1;
            System.out.println(j + "." + ls.get(i));
        }
    }

    /**
     * Print a statement to inform the user that
     * the task has been marked as undone.
     * @param task The line of task to be marked as undone.
     */
    public void printMarkUndone(String task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Print a statement to inform the user that
     * the task has been marked as done.
     * @param str The line of task to be marked as done.
     */
    public void printMarkDone(String str) {
        System.out.println("Nice! I've marked this task as done:\n" + str);
    }

    /**
     * Print a statement to inform the user that
     * the task has been removed and the number of tasks left.
     * @param str The line of task to be removed.
     * @param size The size of the list, which is the number of tasks left.
     */
    public void printRemoveTask(String str, int size) {
        System.out.println("Noted. I've removed this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Print a statement to inform the user that
     * the task has been added and the number of tasks left.
     * @param str The line of task to be added.
     * @param size The size of the list, which is the number of tasks left.
     */
    public void printAddTask(String str, int size) {
        System.out.println("Got it. I've added this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n");
    }

    /**
     * To close the scanner.
     */
    public void closeScanner() {
        sc.close();
    }
}
