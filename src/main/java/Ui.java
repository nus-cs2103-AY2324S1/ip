import java.util.Scanner;

public class Ui {

    private static String name = "Hachi";

    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out the UI line element.
     */
    public void showWelcome() {
        showLine();
        System.out.println("What's good my bro! People round these parts call me " + name
                + ".\nWhat can I do for ya sonny?");
        showLine();
    }

    /**
     * Prints out the error message of the exception
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Returns the next command typed by the user
     */
    public String getInput() {
        return sc.nextLine();
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }
}