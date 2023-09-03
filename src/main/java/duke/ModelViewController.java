package duke;

import java.io.PrintStream;

/**
 * A class that captures the output of the chatbot and transfers information to the GUI.
 */
public class ModelViewController {
    private Duke chatBot;

    /**
     * Constructor for ModelViewController.
     */
    public ModelViewController() {
        // Make sense to not take in a Duke object since we will only have 1 chat bot
        this.chatBot = new Duke("./data/duke.txt");
    }

    /**
     * Returns a response object that stores the response string.
     * @param input the input to run the chatbot with
     * @return  a response object that stores the response string.
     */
    public Response getResponse(String input) {
        ConsoleOutputStream consoleOutputStream = new ConsoleOutputStream();

        // Original System.out object
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(consoleOutputStream));

        // Run the chatbot and get all printed responses
        chatBot.run(input);

        // Restore original system.out
        System.setOut(originalOut);

        // Getting output from captured output stream
        String output = consoleOutputStream.getContent();

        // A response object to store the string
        Response response = new Response(output);
        return response;
    }

    /**
     * Returns a response object that stores the welcome message.
     * @return  a response object that stores the welcome message.
     */
    public Response createWelcomeMessage() {
        ConsoleOutputStream consoleOutputStream = new ConsoleOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(consoleOutputStream));
        chatBot.welcome();
        System.setOut(originalOut);
        String welcomeMessage = consoleOutputStream.getContent();
        Response response = new Response(welcomeMessage);
        return response;
    }
}
