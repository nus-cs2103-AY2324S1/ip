package duke;

/**
 * The `Ui` class handles user interface-related functionality, including displaying messages and greetings.
 */
public class Ui {
    private String greeting;
    private String exitGreeting;
    private String horizontalBar;
    private String logo;

    /**
     * Constructs a new `Ui` instance with default greetings, horizontal bar, and logo.
     */
    public Ui() {
        this.greeting = "Hello, I'm Capt. Price! What can I do for you today, Sergeant?";
        this.exitGreeting = "Over and out. See you next mission!";
        this.horizontalBar = "-------------------------------------------------";
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    /**
     * Constructs a new `Ui` instance with custom greetings, horizontal bar, and logo.
     *
     * @param greeting      The custom greeting message.
     * @param exitGreeting  The custom exit greeting message.
     * @param horizontalBar The custom horizontal bar string.
     * @param logo          The custom logo string.
     */
    public Ui(String greeting, String exitGreeting, String horizontalBar, String logo) {
        this.greeting = greeting;
        this.exitGreeting = exitGreeting;
        this.horizontalBar = horizontalBar;
        this.logo = logo;
    }

    /**
     * Formats a message with a horizontal bar and spaces for display.
     *
     * @param message The message to format.
     * @return The formatted message.
     */
    private String botMessage(String message) {
        String space = "    ";
        return space + this.horizontalBar + "\n" + space + message + "\n" + space + this.horizontalBar;
    }

    /**
     * Displays the greeting message.
     */
    public void displayGreeting() {
        System.out.println(this.botMessage(this.greeting));
    }

    /**
     * Displays the exit greeting message.
     */
    public void displayExitGreeting() {
        System.out.println(this.botMessage(this.exitGreeting));
    }

    /**
     * Displays a message using the botMessage formatting.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(botMessage(message));
    }
}
