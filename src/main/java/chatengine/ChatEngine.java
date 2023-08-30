package chatengine;

import io.IOHandler;
import io.ConsoleIO;

public class ChatEngine {
    private IOHandler ioHandler;

    public ChatEngine() {
        this.ioHandler = new ConsoleIO();
    }

    public void start() {
        ioHandler.greet();
        String input;
        while(!(input = ioHandler.readInput()).equals("bye")) {
            String output = processInput(input);
            ioHandler.writeOutput(output);
        }
        ioHandler.sayGoodbye();
    }

    public String processInput(String input) {
        // Process the input and return a response
        return input;
    }
}
