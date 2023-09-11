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
    public static String greet(){
        return "Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n";
    }

    /**
     * To say goodbye to the user when the program ends.
     */
    public String bye(){
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * To print every element in the list.
     * @param size The size of the list you wish to print.
     * @param ls The List<Task> you wish to print.
     */
    public String printList(int size, List<Task> ls){
        String list = "";
        for(int i = 0; i < size;i++) {
            int j = i + 1;
            list = list + "\n" + j + "." + ls.get(i);
        }
        return "Here are the tasks in your list: " + list;
    }

    /**
     * Print a statement to inform the user that
     * the task has been marked as undone.
     * @param task The line of task to be marked as undone.
     */
    public String printMarkUndone(String task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Print a statement to inform the user that
     * the task has been marked as done.
     * @param str The line of task to be marked as done.
     */
    public String printMarkDone(String str) {
        return "Nice! I've marked this task as done:\n" + str;
    }

    /**
     * Print a statement to inform the user that
     * the task has been removed and the number of tasks left.
     * @param str The line of task to be removed.
     * @param size The size of the list, which is the number of tasks left.
     */
    public String printRemoveTask(String str, int size) {
        return "Noted. I've removed this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Print a statement to inform the user that
     * the task has been added and the number of tasks left.
     * @param str The line of task to be added.
     * @param size The size of the list, which is the number of tasks left.
     */
    public String printAddTask(String str, int size) {
        return "Got it. I've added this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n";
    }

    public String printFilterList(List<Task> filteredList) {
        String header = "Here are the matching tasks in your list: ";
        String printList = "";
        for(int i = 0; i < filteredList.size() ;i++) {
            int j = i + 1;
            printList = printList + "\n" + j + "." + filteredList.get(i);
        }
        return header + printList;
    }

    public String help() {
        String header = "Hello! I am glad to help you! \n";
        String listHelp = "To get overview of your list --> type: list \n";
        String todoHelp = "If you want to add a todo task --> type: todo TASKNAME \n";
        String deadlineHelp = "If you want to add a deadline task --> type: deadline TASKNAME /by yyyy-mm-dd \n";
        String eventHelp = "If you want to add an evenmt task --> type: event TASKNAME /from Mon 2pm /to 4pm \n";
        String markHelp = "To mark or unmark a task --> type: mark 2 or unmark 3 \n";
        String deleteHelp = "To delete a task --> type: delete 5 \n";
        String findHelp = "To find a task --> type: find KEYWORD \n";
        String closing = "Hope that helps!";
        return header + "\n" + listHelp + "\n" + todoHelp + deadlineHelp + eventHelp
                + "\n" + markHelp + deleteHelp + findHelp + "\n" + closing;
    }

    /**
     * To close the scanner.
     */
    public void closeScanner() {
        sc.close();
    }
}
