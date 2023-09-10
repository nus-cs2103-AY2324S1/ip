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
        // Only 1 duke object is created in the whole program, with hard coded file path
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

        // Run chat bot for each input
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
        String welcomeMessage = chatBot.craftWelcome();
        Response response = new Response(welcomeMessage);
        return response;
    }
}
