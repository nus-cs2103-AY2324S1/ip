package duke;

import command.UserInterface;

/**
 * Duke is main class that controls the whole flow of the chatbot which makes of other classes like
 * Storage, TaskManager, UserInterface.
 */
public class Duke {

    private Storage storage;
    private TaskManager taskManager;
    private UserInterface userInterface;
    private Parser parser;

    /**
     * Constructor which instantiates a new Duke.
     *
     */
    public Duke() {
        //Default constructor
        userInterface = new UserInterface();
        storage = new Storage("data/duke.txt");
        taskManager = new TaskManager(storage.load());
        parser = new Parser(taskManager, userInterface, storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Nila: \n" + parser.parseCommand(input);
    }


}


