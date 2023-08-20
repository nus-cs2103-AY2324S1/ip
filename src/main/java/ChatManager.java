import message.ByeMessage;
import message.AddedMessage;
import message.WelcomeMessage;

public class ChatManager {
    private boolean isActive;
    private TodoList todoList;
    public ChatManager() {
        this.isActive = true;
        this.todoList = new TodoList();
        new WelcomeMessage().send();
    }
    public void handleInput(String userInput) {
        if (userInput.equals("bye")) {
            this.isActive = false;
            new ByeMessage().send();
            return;
        }
        if (userInput.equals("list")) {
            todoList.printList();
            return;
        }
        todoList.add(userInput);
    }
    public boolean getIsActive() {
        return this.isActive;
    }
}
