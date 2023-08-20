import java.util.Scanner; // Import the Scanner class

public class Duck {

    /**
     * Default tab spacing
     */
    private static final String TAB = "     ";
    /**
     * Default Welcome Message
     */
    private static final String WELCOME_MESSAGE = TAB + "Quack Quack! I am a duck named Quack\r\n"
            + TAB + "What can I do for you?\r\n";

    /**
     * Default Exit Message
     */
    private static final String GOODBYE_MESSAGE = TAB + "Quack Quack! Quack hopes to see you again soon!\r\n";

    /**
     * Line Break
     */
    private static final String LINE_BREAK = "    ____________________________________________________________\r\n";

    /**
     * App LOGO
     */
    private static final String LOGO = "\r\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n"
            +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░████░░██████████░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░████░░██▒▒▒▒▒▒██░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░██░░██░░░░░░░░██░░░░░░░░░░░░░░██▒▒▒▒▒▒██░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░██░░░░██░░░░░░██░░░░░░░░░░░░░░████████░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░██░░░░░░██░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░████████████░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░████████████████░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░";

    /**
     * Stores the user inputs
     */
    private String[] todoList = new String[100];

    /**
     * Stores the current index of the todo List
     */
    private int todoListIndex = 0;

    public static void main(String[] args) {
        new Duck().run();
    }

    /**
     * Entry point of the software
     */
    public void run() {
        // Welcome Message
        print(Duck.LOGO);
        print(Duck.LINE_BREAK + Duck.WELCOME_MESSAGE + Duck.LINE_BREAK);

        this.collectCommand();

        // Good bye Message
        print(Duck.LINE_BREAK + Duck.GOODBYE_MESSAGE + Duck.LINE_BREAK);
    }

    /**
     * Handles the collection of the command
     */
    public void collectCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            print(Duck.LINE_BREAK);
            this.handleInput(input);
            print(Duck.LINE_BREAK);
            input = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Handles the execution of command
     * 
     * @param command - the command being executed
     */
    public void handleInput(String command) {

        if (command.equals("list")) {
            if (this.todoListIndex == 0) {
                this.print("Quack Quack, you have not entered anything yet!");
            }
            for (int i = 0; i < this.todoListIndex; i++) {
                this.print(String.format("%d: %s", i + 1, this.todoList[i]));
            }

        } else {
            if (this.todoListIndex >= 99) {
                this.print("Quack Quack, I cant remember anymore things!");
                return;
            }
            this.todoList[this.todoListIndex++] = command;
            this.print(String.format("added: %s", command));
        }
    }

    /**
     * Handles the formating of string being printed
     * 
     * @param string - the string being printed
     */
    public void print(String string) {
        if (string.startsWith(Duck.LINE_BREAK)) {
            System.out.println(string);
            return;
        }
        System.out.println(Duck.TAB + string);
    }
}
