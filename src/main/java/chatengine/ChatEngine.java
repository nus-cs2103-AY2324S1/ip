package chatengine;

import io.IOHandler;
import io.ConsoleIO;

public class ChatEngine {
    private IOHandler ioHandler;
    private String[] userTextDB;
    private int textCounter;

    public ChatEngine() {
        this.ioHandler = new ConsoleIO();
        this.userTextDB = new String[100];
        this.textCounter = 0;
    }

    public void start() {
        ioHandler.greet();
        String input;
        while(!(input = ioHandler.readInput()).equals("bye")) {
            processInput(input);
        }
        ioHandler.sayGoodbye();
    }

    private void processInput(String input) {
        // Process the input
        if (input.equals("list")) {
            processDB(userTextDB);
        } else {
            userTextDB[textCounter] = input;
            textCounter++;
            ioHandler.writeOutput(String.format("added: %s", input));
        }
    }

    private void processDB(String[] texts) {
        for (int i = 0; i < texts.length && texts[i] != null; i++) {
            String output = String.format("%d. %s", i+1, texts[i]);
            ioHandler.writeOutput(output);
        }
    }
}
