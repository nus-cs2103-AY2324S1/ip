package kevin.ui;

/**
 * A class responsible to output to the console.
 */
public class Logger {
    /**
     * Prints the text wrapped in horizontal lines.
     * @param str
     */
    public void log(String str) {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + str);
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static final String HORIZONTAL_LINE = "_".repeat(70);

    public static final String BOT_NAME = "\t \n" +
            "\t" + " _   __ _____ _   _ _____ _   _ \n" +
            "\t" +"| | / /|  ___| | | |_   _| \\ | |\n" +
            "\t" +"| |/ / | |__ | | | | | | |  \\| |\n" +
            "\t" +"|    \\ |  __|| | | | | | | . ` |\n" +
            "\t" +"| |\\  \\| |___\\ \\_/ /_| |_| |\\  |\n" +
            "\t" +"\\_| \\_/\\____/ \\___/ \\___/\\_| \\_/\n";

    /**
     * Prints the hello message when the program first runs.
     */
    public void hello() {
        String welcomeMessage = "Hello! I'm" + BOT_NAME + "\n\t" + "What can I do for you?";
        this.log(welcomeMessage);
    }

    /**
     * Prints the bye message before the program ends.
     */
    public void bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        this.log(goodbyeMessage);
    }
}
