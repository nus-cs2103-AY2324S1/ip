package moss;

/**
 * The UI class handles user interface interactions and provides messages for the application.
 */
public class UI {

    /**
     * Displays a greeting message when the application starts.
     */
    public String greeting() {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Moss \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        return greet;
    }

    /**
     * Displays a farewell message when the application is about to exit.
     */
    public String bye() {
        String bye = "________________________________________________________\n"
                +"Bye. Hope to see you again soon! \n"
                + "________________________________________________________";
        return bye;
    }

}

