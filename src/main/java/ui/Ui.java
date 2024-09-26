package main.java.ui;

public class Ui {

    /**
     * Greets the user with a welcome message.
     *
     * @param name The bot name to use in the greeting.
     */
    public void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }
    /**
     * Prints a goodbye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
