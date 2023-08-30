package duke;

import java.io.File;

public class Duke {

    /**
     * Handles commands and messages to ChatBot
     * @param args CLI args. No implementation.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(DukeEnvironmentConstants.FILE_PATH);
        Parser parser = new Parser();
        ChatBotList list = new ChatBotList(storage.loadList());
        System.out.println(new File(DukeEnvironmentConstants.FILE_PATH));
        while (true) {
            String input = ui.getNextLine().trim();
            try {
                Command c = parser.parse(input);
                c.execute(list, ui, storage);
                if (c instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.showException(e);
            }
        }
        ui.close();
    }
}
