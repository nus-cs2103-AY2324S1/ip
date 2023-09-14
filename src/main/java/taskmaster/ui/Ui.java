package taskmaster.ui;
public class Ui {
    /**
     * Line to separate blocks
     */
    public static final String line = "____________________________________________________________";

    public static final String WELCOME_MESSAGE = "Hello! I am TaskMaster!\n" + "What can I do for you today?\n";
    public static final String GOODBYE_MESSAGE = "Bye! Hope to see you again";

    /**
     * Prints a welcome message.
     */
    public void printHello() {
        System.out.println(line);
        System.out.println("Hello! I am TaskMaster!");
        System.out.println("What can I do for you today?");
        System.out.println(line);
    }

}
