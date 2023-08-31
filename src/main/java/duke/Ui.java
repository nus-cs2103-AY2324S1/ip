package duke;
public class Ui {
    private String line = "    ______________________________________________";

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println(line + "\n    Hello, I'm your task manager :)\n    What can I do for you?\n" + line);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
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
    public void oops() {
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
