package duke;
public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}

