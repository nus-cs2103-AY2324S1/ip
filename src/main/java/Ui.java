import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String CHATBOT_NAME = "Duke";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }

    public void showWelcome() {
        showLine();
        String message = "Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?";
        showMessage(message);
        showLine();
    }

    public void showGoodbye() {
        String message = "Bye. Hope to see you again soon!";
        showMessage(message);
    }

    public void showTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            showInputWithIndentation((i + 1) + "." + tasks.get(i));
        }
    }

    public void showAddTask(Task task, int numberOfTasks) {
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + numberOfTasks
                + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        showMessage(message);
    }

    public void showDeleteTask(Task task, int numberOfTasks) {
        String message = "Noted. I've removed this task:\n  " + task + "\nNow you have " + numberOfTasks
                + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        showMessage(message);
    }

    public void showMarkTaskAsDone(Task task) {
        String message = "Nice! I've marked this task as done:\n  " + task;
        showMessage(message);
    }

    public void showUnmarkTaskAsDone(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task;
        showMessage(message);
    }

    public void showLine() {
        showInputWithIndentation(HORIZONTAL_LINE);
    }

    private void showInputWithIndentation(String input) {
        System.out.println("    " + input);
    }

    private void showMessage(String input) {
        String[] lines = input.split("\n");
        for (String line : lines) {
            showInputWithIndentation(line);
        }
    }

    public void showError(String input) {
        showMessage(input);
    }

    public void showLoadingError() {
        showError("OOPS!!! I have problems loading your tasks.");
    }
}
