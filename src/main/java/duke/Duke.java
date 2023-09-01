package duke;

/**
 * This represents the main entry point of the Duke program.
 */
public class Duke {
    /** The name of the bot. */
    private static final String NAME = "Nino!";
    /**
     * runs the Duke program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            UserInterface userInterface = new UserInterface(new Storage(), new StoreList());

            System.out.println("Hello, my name is " + Duke.NAME);
            UserInterface.display("What can I do for you?");
            userInterface.start();
            userInterface.readCommandLine();
            userInterface.exit();

        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
