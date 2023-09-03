

/**
 * The Duke class helps to manage all messages sent by the user to the chatbot accordingly.
 */
public class Duke {

    //Parses the commands given by the user.
    private Parser parser;


    /**
     * Instantiates an instance of the Duke bot.
     * @param path The filepath to the data.s file.
     */
    public Duke(String path) {
        this.parser = new Parser(new DukeList(path));
    }

    /**
     * The main class for which the chatbot runs.
     * @param args The inputs given by the user in the command prompt.
     */
    public static void main(String[] args) {
        final String DATA_DIRECTORY = "data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + DATA_DIRECTORY + "/tasks.s";

        Duke lati = new Duke(dataFilePath);

        lati.run();
    }

    /**
     * Takes inputs and runs the Duke bot.
     */
    public void run() {
        UI.greet();
        boolean isOpen = true;

        while (isOpen) {
            String command = UI.scan();
            this.messageHandler(command);
            isOpen = !command.equals("bye");
        }

        this.parser.close();
    }

    /**
     * Parses and manages the user's inputs using the parser.
     * @param tally takes in the input string.
     */
    public void messageHandler(String tally) {
        this.parser.messageHandler(tally);
    }


}
