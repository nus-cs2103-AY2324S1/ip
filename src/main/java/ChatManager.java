import java.util.regex.Pattern;

public class ChatManager {
    private boolean isActive;
    private final TaskList taskList;
    public ChatManager() {
        this.isActive = true;
        this.taskList = new TaskList();
        new WelcomeMessage().send();
    }
    public void handleInput(String userInput) {
        if (userInput.equals("bye")) {
            this.isActive = false;
            new ByeMessage().send();
            return;
        }
        if (userInput.equals("list")) {
            taskList.printList();
            return;
        }
        if (Pattern.matches("mark \\d", userInput)) {
            String[] arr = userInput.split(" ");
            int num = Integer.parseInt(arr[arr.length - 1]);
            taskList.markTask(num);
            return;
        }
        if (Pattern.matches("unmark \\d", userInput)) {
            String[] arr = userInput.split(" ");
            int num = Integer.parseInt(arr[arr.length - 1]);
            taskList.unmarkTask(num);
            return;
        }
        taskList.add(userInput);
    }
    public boolean getIsActive() {
        return this.isActive;
    }
}
