package duke;

import java.io.FileNotFoundException;

/**
 * Starts the chatbot
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final TaskList list;


    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
//       try {
        list = new TaskList(storage.load());
//        } catch (Exceptions.DukeException e) {
//            ui.showLoadingError();
//            tasks = new duke.TaskList();
//        }
    }

    /**
     * ALlows the chatbbot to run
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Parser parser = new Parser();
        while (!isExit) {
            String input = ui.getUserCommand();
            parser.parse(input, list);
            isExit = ui.isExit(input);
        }
        storage.save(list);
    }

    /**
     * Executes Duke
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke("duke.txt").run();
    }
}


