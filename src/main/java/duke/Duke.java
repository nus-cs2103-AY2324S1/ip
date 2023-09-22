package duke;

public class Duke {

    private Storage storage;
    private Parser parser;
    private ChatBotList list;

    public Duke() {
        this.storage = new Storage(DukeEnvironmentConstants.FILE_PATH);
        this.parser = new Parser();
        this.list = new ChatBotList(storage.loadList());
    }
    
    /**
     * Handles commands and messages to ChatBot
     * @param args CLI args. No implementation.
     */
    public String run(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(list, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
