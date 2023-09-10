package duke;

import duke.utils.Session;

/**
 * The Duke class serves as the entry point to the Duke application.
 * It creates an instance of the Session class and starts the chatbot session.
 */
public class Duke {
    public static void main(String[] args) {
        Session chatBot = new Session();
        chatBot.start();
    }
}
