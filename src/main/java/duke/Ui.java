package duke;

/**
 * Used to handle user interface output.
 *
 * @author Teo Kai Sheng
 */
public class Ui {
    private String line = "    ______________________________________________";

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println(line + "\n    Hello, I'm your task manager :)\n    What can I do for you?\n" + line);
    }

    public String initialResponse() {
        return "Hello, I'm your task manager :)\n    What can I do for you?";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String bye() {
        System.out.println("    Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a horizontal line.
     */
    public void horizontalLine() {
        System.out.println(line);
    }

    /**
     * Displays an error message.
     */
    public String printErrorMessage() {
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
