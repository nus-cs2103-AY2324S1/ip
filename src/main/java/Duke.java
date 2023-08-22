public class Duke {
    private static final String NAME = "Joi";

    private void run() {
        this.greeting();
    }

    private void greeting() {
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?\n");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke joi = new Duke();
        joi.run();
    }
}
