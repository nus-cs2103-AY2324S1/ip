import java.util.Scanner;

public class Duke {
    private static final String NAME = "Joi";
    private boolean isRuning = true;
    private Scanner sc = new Scanner(System.in);

    // the event loop
    private void run() {
        this.greeting();

        while (this.isRuning) {
            String input = this.getUserInput();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                this.isRuning = false;
            }
            else {
                System.out.println(input + "\n");
            }
        }
    }

    private void greeting() {
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?\n");
    }

    private String getUserInput() {
        return this.sc.nextLine();
    }

    public static void main(String[] args) {
        Duke joi = new Duke();
        joi.run();
    }
}
