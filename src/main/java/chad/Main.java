package chad;

import chad.chatengine.ChatEngine;

/**
 * The entry point for the Chad application.
 */
public class Main {
    /**
     * The main method that starts the ChatEngine.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        ChatEngine chatEngine = new ChatEngine("./data/tasks.txt");
        chatEngine.start();
    }
}
