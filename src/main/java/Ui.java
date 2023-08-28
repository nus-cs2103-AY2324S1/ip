public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Victor\n" +
                "What can I do for you?\n----------\n");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("----------\n");
    }
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
