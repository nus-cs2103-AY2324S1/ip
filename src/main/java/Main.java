import chatengine.ChatEngine;

public class Main {
    public static void main(String[] args) {
        ChatEngine chatEngine = new ChatEngine("./data/tasks.txt");
        chatEngine.start();
    }
}
