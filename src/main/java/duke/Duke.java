package duke;

import javafx.scene.image.Image;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList toDo;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/batblob1.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/batblob2.png"));

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            return dukeReply(input);
        } catch (DukeException e) {
            return "Sorry, I don't understand that o(TヘTo)";
        }

    }

    /**
     * Gets the reply from the Duke chatbot based on the user input.
     * @param userInput the command given by the user.
     * @return reply from Duke.
     * @throws DukeException when user input is invalid.
     */

    public String dukeReply(String userInput) throws DukeException {
        ui = new Ui();
        storage = new Storage("duke.txt");
        toDo = new TaskList();
        storage.load();
        String respondMsg = "Got it!";
        Parser parseCommand = new Parser(userInput);
        try {
            respondMsg = parseCommand.parseAndRespond();
        } catch (DukeException e) {
            respondMsg = e.getMessage();
        }
        return respondMsg;
    }

}
