import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String NAME = "Joi";
    private boolean isRunning;
    private final Scanner sc;
    private final ArrayList<String> itemList;

    // constructor for Duke
    public Duke() {
        this.isRunning = true;
        this.sc = new Scanner(System.in);
        this.itemList = new ArrayList<>();
    }

    // the event loop
    private void run() {
        this.greeting();

        while (this.isRunning) {
            String input = this.getUserInput();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                this.isRunning = false;
            } else if (input.equals("list")) {
                for (int i = 0; i < this.itemList.size(); i++) {
                    System.out.println((i+1) + ". " + this.itemList.get(i));
                }
                System.out.println();
            } else {
                this.itemList.add(input);
                System.out.println("added: " + input + "\n");
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
