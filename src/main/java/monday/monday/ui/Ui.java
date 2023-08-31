package monday.monday.ui;

/** 
 * Ui class is responsible for handling user interface related functions. 
 */ 
public class Ui {
    /**
     * Prints a greeting message to the console.
     */
    public static void greet() {
        System.out.println("Hello! I'm " + "Monday");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a farewell message to the console.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a separator line to the console.
     */
    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
