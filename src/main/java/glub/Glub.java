package glub;

import glub.task.TaskList;

/**
 * A task management chatbot.
 */

public class Glub {
    /** Tasklist of the Glub object. */
    private TaskList taskList;
    /** Parser of the Glub object. */
    private Parser parser;
    /**
     * Create a Glub object with a Storage object to initialise the chatbot.
     *
     * @param storage The required storage object.
     */
    public Glub(Storage storage) {
        try {
            this.taskList = new TaskList(storage);
        } catch (GlubException ex) {
            Ui.printError(ex.getMessage());
        }
        this.parser = new Parser(taskList, storage);
    }


    public String getResponse(String userInput) {
        String response;
        try {
            response = this.parser.parse(userInput);
        } catch (GlubException e) {
            response = Ui.printError(e.getMessage());
        }
        return response;
    }

}
