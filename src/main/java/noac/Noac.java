package noac;

import noac.command.Command;
import noac.util.*;


/**
 * Main class of the Noac Chatbot.
 */
public class Noac{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Noac() {
        this("data/tasks.txt");
    }

    /**
     * Initialise the storage, tasklist and ui objects.
     *
     * @param filePath File path for where to save the save file.
     */
    public Noac(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NoacException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }


    /**
     * Retrives the response for the user input.
     *
     * @param input String input given by user.
     * @return Reply given back to user.
     */
    public String getResponse(String input){
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (NoacException e) {
            return ui.showErrorMessage(e);
        }
    }


}



