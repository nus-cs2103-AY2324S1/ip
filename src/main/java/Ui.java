import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public void showWelcomeMessage() {
        System.out.println("Hi! This is your AI assistant LoyBoy!");
        System.out.println("What can I do for you today?");
    }
    public String getUserInput() {
        System.out.print("Enter a command: ");
        return scanner.nextLine();
    }
    public void showTaskAddedMessage(Task task, int totalTasks) {
        System.out.println("Got it! I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list.");
    }
    public void showGoodbyeMessage() {
        System.out.println("I wish you a pleasant day ahead, goodbye!");
    }
    public void showError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }
    public void showLoadingError() {
        System.err.println("Error! Cannot load tasks from data file.");
    }
    public void showSavingError() {
        System.err.println("Error! Cannot save tasks to data file.");
    }
    public void closeScanner() {
        scanner.close();
    }
}
