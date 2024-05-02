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
        String triviaPath = projectRoot + "/" + dataDirectory + "/trivia.s";
        this.parser = new Parser(new TaskList(dataPath), new TriviaList(triviaPath));
    }

    /**
     * Parses and manages the user's inputs using the parser.
     * @param message takes in the input string.
     * @return the message string after parsing and executing the command.
     */
    public String messageHandler(String message) {
        try {
            return this.parser.messageHandler(message).execute();
        } catch (NullPointerException e) {
            return Ui.inputErrorMessage(" ");
        }
    }


}
