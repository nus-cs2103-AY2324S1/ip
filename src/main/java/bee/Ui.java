package bee;

/**
 * Represents the User Interface (UI) of the chatbot.
 * Provides methods to display greetings, loading errors, and farewell messages.
 */
public class Ui {

    /**
     * Displays a greeting message along with the chatbot's logo.
     */
    public void greet() {
        String logo = "\n" +
                "__________\n" +
                "\\______   \\ ____   ____\n" +
                " |    |  _// __ \\_/ __ \\\n" +
                " |    |   \\  ___/\\  ___/\n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/\n";
        System.out.println("Hello! I'm" + logo);
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");
    }

    /**
     * Displays an error message indicating a failure to load tasks.
     */
    public void showLoadingError() {
        System.out.println("~Bzzzz~ Oh no! We failed to load the tasks\n");
    }

    /**
     * Displays a farewell message to bid the user goodbye.
     */
    public void farewell() {
        System.out.println("Bye-bye! Have a great day! ~Bzzz~");
    }
}
