package taskmaster.ui;
public class Ui {
    /**
     * Line to separate blocks
     */
    public static final String line = "____________________________________________________________";

    /**
     * Prints a welcome message.
     */
    public void printHello() {
        System.out.println(line);
        System.out.println("Hello! I am TaskMaster!");
        System.out.println("What can I do for you today?");
        System.out.println(line);
    }
    /**
     * Prints a goodbye message.
     */
    public void printGoodbye() {
        System.out.println(line);
        System.out.println("Bye! Hope to see you again!");
        System.out.println(line);
    }
}
