import java.util.Scanner;

public class Ui {
    private final String logo;
    private final Scanner inputScanner;

    public Ui(String logo) {
        this.logo = logo;
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Generates a horizontal line to divide parts of the conversation.
     */
    public void addDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Makes the bot say something.
     * Formatted nicely with a divider.
     */
    public void say(String line) {
        System.out.println(line);
        addDivider();
    }

    /**
     * Displays the logo of the bot.
     */
    public void showLogo() {
        say(this.logo);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        say("Hi, I am CRUSADER\nWhat can I do for you?");
    }

    /**
     * Says goodbye to the user.
     */
    public void farewell() {
        say("Bye!\nHave a great day!");
    }

    /**
     * Prompts the user for input.
     */
    public String promptInput() {
        System.out.printf("Input: ");
        String prompt = inputScanner.nextLine();
        addDivider();
        return prompt.trim();
    }
}
