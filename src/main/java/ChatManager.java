import java.util.regex.Pattern;

public class ChatManager {
    private boolean isActive;
    private final TaskList taskList;
    public ChatManager() {
        this.isActive = true;
        this.taskList = new TaskList();
        new WelcomeMessage().send();
    }
    public void handleInput(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            this.isActive = false;
            new ByeMessage().send();
            return;
        }
        if (userInput.equals("list")) {
            taskList.printList();
            return;
        }
        if (Pattern.matches("mark \\d+", userInput)) {
            int num = Integer.parseInt(userInput.split(" ", 2)[1]);
            taskList.markTask(num);
            return;
        }
        if (Pattern.matches("unmark \\d+", userInput)) {
            int num = Integer.parseInt(userInput.split(" ", 2)[1]);
            taskList.unmarkTask(num);
            return;
        }
        if (Pattern.matches("^todo\\s*$", userInput)) {
            // raise exception
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        if (Pattern.matches("todo .+", userInput)) {
            String name = userInput.split(" ", 2)[1];
            taskList.add(new TodoTask(name));
            return;
        }
        if (Pattern.matches("deadline .+ /by .+", userInput)) {
            // assumes " /by " is not contained in deadline name
            String[] arr = userInput.split(" /by ", 2);
            String name = arr[0].split(" ", 2)[1];
            String deadline = arr[1];
            taskList.add(new DeadlinesTask(name, deadline));
            return;
        }
        if (Pattern.matches("event .+ /from .+ /to .+", userInput)) {
            // assumes " /to " is not in event name and from date
            String[] a1 = userInput.split(" /to ", 2);
            // assumes " /from " is not in event name
            String[] a2 = a1[0].split(" /from ", 2);
            String name = a2[0].split(" ", 2)[1];
            String from = a2[1];
            String to = a1[1];
            taskList.add(new EventsTask(name, from, to));
            return;
        }
        if (Pattern.matches("delete \\d+", userInput)) {
            int num = Integer.parseInt(userInput.split(" ", 2)[1]);
            taskList.delete(num);
            return;
        }
        new MenuMessage().send();
    }
    public boolean getIsActive() {
        return this.isActive;
    }
}
