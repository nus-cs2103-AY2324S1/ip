package duke;


/**
 * The Duke class helps to manage all messages sent by the user to the chatbot accordingly.
 */
public class Duke {

    //Parses the commands given by the user.
    private Parser parser;


    /**
     * Instantiates an instance of the Duke bot.
     */
    public Duke() {
        final String dataDirectory = "data";
        String projectRoot = System.getProperty("user.dir");
        String dataPath = projectRoot + "/" + dataDirectory + "/tasks.s";
        this.parser = new Parser(new TaskList(dataPath));
    }

    /**
     * Parses and manages the user's inputs using the parser.
     * @param message takes in the input string.
     * @return the message string after parsing and executing the command.
     */
    public String messageHandler(String message) {
        return this.parser.messageHandler(message).execute();
    }


}
