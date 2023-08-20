import message.ByeMessage;
import message.EchoMessage;
import message.WelcomeMessage;

public class ChatManager {
    private boolean isActive;
    public ChatManager() {
        this.isActive = true;
        new WelcomeMessage().send();
    }
    public void handleInput(String userInput) {
        if (userInput.equals("bye")) {
            this.isActive = false;
            new ByeMessage().send();
            return;
        }
        new EchoMessage(userInput).send();
    }
    public boolean getIsActive() {
        return this.isActive;
    }
}
