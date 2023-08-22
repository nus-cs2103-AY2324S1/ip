public class Duke {
    /** The name of the chatbot. */
    private static final String NAME = "Bro";

    /** Greets the user. */
    public void greet() {
        System.out.println("Hello! I'm " + NAME + "\n" + "What can I do for you? \n");
    }

    /** Causes chatbot to exit. */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.greet();
        chatbot.exit();
    }
}
