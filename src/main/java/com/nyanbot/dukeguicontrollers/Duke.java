package com.nyanbot.dukeguicontrollers;

import com.nyanbot.dukeparsers.DukeParser;
import com.nyanbot.dukestorage.DukeStorageDatabase;
import com.nyanbot.duketasklist.DukeTaskList;
import com.nyanbot.dukeuiclasses.DukeUi;

import java.io.IOException;

/**
 * This class encapsulates the com.nyanbot.
 *
 * @author Tan Kerway
 * @see <a href="https://se-education.org/guides/tutorials/javaFxPart2.html">credits</a>
 */
public class Duke {
    // ui class for normal ui displays to the user
    private final DukeUi ui;
    // storage class for db operations
    private final DukeStorageDatabase databaseController;
    // tasklist class for manipulating the tasklist
    private final DukeTaskList taskList;
    // parser class to parse input
    private final DukeParser parser;

    /**
     * Constructs an instance of a chatbot class.
     *
     * @author Tan Kerway
     */
    public Duke() {
        this.ui = new DukeUi();
        this.taskList = new DukeTaskList();
        this.databaseController = new DukeStorageDatabase(taskList);
        this.parser = new DukeParser(this.taskList);

        try {
            this.taskList.setDatabaseController(this.databaseController);
        } catch (IOException e) {
            System.out.println("There was an issue accessing my nyanory :c");
        }
        this.taskList.setParser(this.parser);
    }

    /**
     * Function that starts a chat with the user.
     *
     * @author Kerway
     */
    private void initiateChat() {
        try {
            this.databaseController.saveTaskList();    // save the user's task to the database
        } catch (IOException e) {
            System.out.println("There was an issue accessing my nyanory :c");
        }
    }

    /**
     * Returns the greeting string that is parsed by the internal
     * DukeUi instance of this class.
     *
     * @author Tan Kerway
     * @return the greeting string returned by the Ui instance
     */
    String getGreeting() {
        return this.ui.getGreeting(this.taskList.getTasks());
    }

    /**
     * Returns the chatbot response to the user input.
     *
     * @author Tan Kerway
     * @param input the input provided by the user
     * @return the response of the chatbot to the given input
     */
    String getUserResponse(String input) {
        return this.parser.processUserCommand(input);
    }

    /**
     * This main function, when run, will cause the Chat Bot
     * to greet the user, and then exit.
     *
     * @author Tan Kerway
     * @param args pointer to some array of command-line arguments
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        dukeInstance.initiateChat();
    }
}
