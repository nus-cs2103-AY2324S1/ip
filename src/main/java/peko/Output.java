package peko;
/**
 * The `Output` class provides static methods to handle console output and display messages,
 * including an introduction message and an exit message for the Peko chat application.
 */
public class Output {


    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n"
            + "Hololive san kisei no\n"
            + "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";

    /**
     * Displays the introduction message, including a Peko-themed logo and a greeting.
     */
    public static void intro() {
        String pekoLogo = " _____      _\n"
                + "|     |___ | | ______\n"
                + "|  ___/ _ \\| |/ /    \\\n"
                + "| |  <  __/|   <  <>  |\n"
                + "|_|   \\___||_|\\_\\____/";
        System.out.println(pekoLogo);

        System.out.println(lineBreak);
        System.out.println(introText);
        System.out.println(lineBreak);
    }
    /**
     * Displays the exit message when the Peko chat application is exiting.
     */
    public static void exit() {
        System.out.println(exitText);
    }
}
