//public printer class that abstracts and does the printing of the outputs

import java.util.ArrayList;

public class Printer {

    /** line to be drawn on screen */
    static String line = "---------------------";

    static String greeting = " Hello! I'm Somebodyhaha\n" +
            " What can I do for you?";
    static String exiting = " Bye. Hope to See you again soon!";

    /**
     * Prints the greeting onto the console
     */
    public static void printGreeting(){
        print(greeting);
    }

    /**
     * Prints the exit message onto the console
     */
    public static void printExit() {
        print(exiting);
    }

    /**
     * Prints line onto the console
     */
    public static void printLine() {
        System.out.println(line);
    }

    /**
     * Prints the input provided
     * @param input String to be printed
     */
    public static void print(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }

    /**
     * Prints the items found in the task list
     * @param lst The list to be printed
     */
    public static void printList(ArrayList<Task> lst){
        printLine();
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        if(lst != null) {
            for (Task item: lst) {
                if(item == null) {break;}
//                String str = count + ". [" + item.toString() + "] " + item.getDescription();
                System.out.print(count + ". ");
                System.out.println(item);
                count++;
            }
        }
        printLine();
    }

    /**
     * Prints the info of what was added to the list when something is added
     * @param task task description
     * @param count current number of tasks
     */
    public static void addTask(Task task, int count) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (count) + " tasks in the list.");
        printLine();
    }


}
