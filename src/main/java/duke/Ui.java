package duke;

import java.util.Random;

/**
 * The `Ui` class handles user interface-related functionality, including displaying messages and greetings.
 */
public class Ui {
    private String greeting;
    private String exitGreeting;
    private String horizontalBar;
    private String[] quotes = {"Soldier, your mission is clear: Conquer your tasks and bring order to the chaos.",
            "In the war against procrastination, we'll fight tooth and nail to victory, soldier!",
            "Time waits for no one, soldier. Move swiftly and conquer your to-do list.",
            "In the battle of productivity, we lead from the front, taking down tasks one by one.",
            "With you in the battlefield of life, against task enemies!",
            "No retreat, no surrender! We tackle tasks head-on, like true warriors.",
            "Task enemies beware! We're on a relentless mission to accomplish it all.",
            "Every completed task is a step closer to victory. Keep pushing forward!",
            "No excuses, soldier! You are the commander of your tasks, and victory is the only option.",
            "With every task you complete, you weaken the forces of chaos and strengthen the fortress of productivity.",
            "A true warrior of productivity knows that success is achieved through discipline and relentless effort. Keep charging forward!",
            "Stay vigilant, soldier, for the enemy of progress is cunning. But with diligence, you shall overcome."
    };
    private String logo;

    /**
     * Constructs a new `Ui` instance with default greetings, horizontal bar, and logo.
     */
    public Ui() {
        this.greeting = "Hello, I'm Capt. Price! What can I do for you today, Major?";
        this.exitGreeting = "Over and out. See you next mission Major!";
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
     * Returns the entry greeting message.
     */
    public String getEntryGreeting() {
        return this.greeting;
    }

    /**
     * Returns the exit greeting message.
     */
    public String getExitGreeting() {
        return this.exitGreeting;
    }

    /**
     * Displays a message using the botMessage formatting.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(botMessage(message));
    }

    public String getQuote() {
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        return "\n\n `" + quotes[randomIndex] + "` \n     ~ Capt. Price";
    }
}
