import java.util.Scanner;

public class Ui {

    Scanner scanner = new Scanner(System.in);
    static String softbreak = "--------------------------------------------------";
    static String hardbreak = "——————————————————————————————————————————————————";

    public void showLoadingError() {
        System.out.println("Failed to load file.");
    }

    public void showWelcome() {
        System.out.println(hardbreak +
                "\n...Fingerprint match found. Verification complete. Welcome home.\n" +
                "PRTS, at your service. What would you like to do today?\n" + hardbreak);
    }

    public void showGoodbye() {
        System.out.println("Farewell. See you again soon.\n" + hardbreak);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showSoftLine() {
        System.out.println(softbreak);
    }

    public void showHardLine() {
        System.out.println(hardbreak);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void list(TaskList tasks) {

        System.out.println("Here are your tasks for today.");

        System.out.println(tasks);

        System.out.println("You now have " + tasks.getSize() + " tasks in your list.");

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
