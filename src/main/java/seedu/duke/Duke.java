package seedu.duke;

import seedu.duke.command.Command;

/**
 * Represents my version of a chatbot.
 * It is mainly used for recording tasks like
 * todos, deadlines and events.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;

    /**
     * The main constructor for this chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui("Kam_BOT");
        this.startUp("./data/duke.txt");
    }

    public static String greet() {
        return Ui.greet();
    }

    /**
     * Start and set up the chatbot.
     *
     * @param filePath Path of the storage file.
     */
    public void startUp(String filePath) {
        this.taskList.setHardDiskFilePath(filePath);
        this.taskList.loadData();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        String[] response = new String[1];

        try {
            Command c = this.ui.parseUserInput(input);
            c.execute(this.taskList, this.ui, response);
        } catch (Exception e) {
            response[0] = (e.getMessage());
        }

        return response[0];

    }
}
