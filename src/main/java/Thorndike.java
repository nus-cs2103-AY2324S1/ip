public class Thorndike {
    public Thorndike() {

    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        greet();
        end();
    }

    /**
     * Sends greetings to user.
     */
    private void greet() {
        System.out.println("Meow! I'm Thorndike.");
        System.out.println("What can I do for you?");
    }

    /**
     * Terminates the chatbot.
     */
    private void end() {
        System.out.println("Bye meow! Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Thorndike chatbot = new Thorndike();
        chatbot.start();
    }
}
