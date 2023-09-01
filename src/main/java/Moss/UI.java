package moss;

/**
 * The UI class handles user interface interactions and provides messages for the application.
 */
public class UI {

    /**
     * Displays a greeting message when the application starts.
     */
    public void greeting() {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Moss \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(greet);
    }

    /**
     * Displays a farewell message when the application is about to exit.
     */
    public void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a welcome message when the application is launched.
     */
    public void welcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Welcome to Moss.Moss! How can I help you today?");
        System.out.println("____________________________________________________________");
    }
}

