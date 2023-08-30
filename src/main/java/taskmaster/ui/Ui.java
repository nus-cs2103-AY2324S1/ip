package taskmaster.ui;
public class Ui {

    public static final String line = "____________________________________________________________";

    public Ui() {
    }

    public void printHello() {
        System.out.println(line);
        System.out.println("Hello! I am TaskMaster!");
        System.out.println("What can I do for you today?");
        System.out.println(line);
    }
    public void printGoodbye() {
        System.out.println(line);
        System.out.println("Bye! Hope to see you again!");
        System.out.println(line);
    }
}
