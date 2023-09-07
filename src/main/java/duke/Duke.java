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
    // public static void main(String[] args) {
    //     Ui ui = new Ui();
    //     Storage storage = new Storage(DukeEnvironmentConstants.FILE_PATH);
    //     Parser parser = new Parser();
    //     ui.open();
    //     ChatBotList list = new ChatBotList(storage.loadList());
    //     System.out.println(new File(DukeEnvironmentConstants.FILE_PATH));
    //     while (true) {
    //         String input = ui.getNextLine().trim();
    //         try {
    //             Command c = parser.parse(input);
    //             c.execute(list, ui, storage);
    //             if (c instanceof ExitCommand) {
    //                 break;
    //             }
    //         } catch (DukeException e) {
    //             ui.showException(e);
    //         }
    //     }
    //     ui.close();
    // }

    public String run(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(list, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
