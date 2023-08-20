import java.util.Scanner;

/**
 * Juke Virtual Assistant
 */
public class Juke {
    /** Separator used by the virtual assistant to demarcate the start or end of a conversation */
    private final static String SEPARATOR = "\n-----------------------------------------------------------------------\n";

    /**
     * Juke Logo made from ASCII Art. Credits go to:
     * <a href="https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20">...</a>
     */
    private final static String LOGO =
            "       __      __      \n" +
            "      / /_  __/ /_____ \n" +
            " __  / / / / / //_/ _ \\\n" +
            "/ /_/ / /_/ / ,< /  __/\n" +
            "\\____/\\__,_/_/|_|\\___/ ";

    /** Introductory statement used by the assistant when first booting up. */
    private final static String INTRO = "What's up! My name is Juke (J|ava D|uke) and I will be your personal \n" +
            "assistant for today! How may I assist you?";

    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private final static String EXIT = "Goodbye!";

    /** The Virtual Assistant's output CLI prompt. */
    private final static String JUKEOUTPUT = "\u001B[34m" + ">>> " + "\u001B[0m";

    /** The user's input CLI prompt. */
    private final static String JUKEINPUT = "\u001B[34m" + "juke> " + "\u001B[0m";

    /** Scanner instance used to capture user input. */
    private final Scanner jukeScanner;

    /**
     * Constructor for Juke.
     */
    public Juke() {
        this.jukeScanner = new Scanner(System.in);
    }

    /**
     * Main function that is run when this Java file is compiled and executed.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        Juke jukeAssistant = new Juke();
        jukeAssistant.printIntroduction();
        jukeAssistant.echo();
    }

    /**
     * Prints out the Introduction statments.
     */
    private void printIntroduction() {
        StringBuilder builder = new StringBuilder();
        builder.append(Juke.LOGO);
        builder.append(Juke.SEPARATOR);
        builder.append(Juke.INTRO);
        builder.append(Juke.SEPARATOR);

        System.out.print(builder);
    }

    /**
     * Prints out the Exit statements.
     */
    private void printExit() {
        // close the scanner to save resources
        this.jukeScanner.close();

        StringBuilder builder = new StringBuilder();
        builder.append(Juke.EXIT);
        builder.append(Juke.SEPARATOR);

        System.out.print(builder);
    }

    /**
     * Method that either echoes the user's inputs or exits the conversation when "bye"
     * is the command given to Juke.
     */
    private void echo() {
        System.out.print(Juke.JUKEINPUT);
        String command = this.jukeScanner.nextLine();
        String[] parsedCommand = this.parse(command);

        // only an explicit "bye" command will trigger the exit command
        if (parsedCommand.length == 1 && parsedCommand[0].equals("bye")) {
            this.printExit();
        } else {
            System.out.print(Juke.JUKEOUTPUT);
            System.out.print(command);
            System.out.print(Juke.SEPARATOR);
            this.echo();
        }
    }

    /**
     * Parses the input and returns to the caller a sanitised list of commands given to
     * Juke.
     *
     * @param command Raw String command
     * @return String[] containing list of String commands
     */
    private String[] parse(String command) {
        return command.split(" ");
    }
}
