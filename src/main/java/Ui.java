import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        printMessage("Hello! I'm Martin\n     What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("_______________________________");
    }

    public void showError(String message) {
        printMessage("Error: " + message);
    }

    public void showLoadingError() {
        printMessage("Problem encountered while loading data!");
    }

    public void printMessage(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + message);
        System.out.println("    ____________________________________________________________");
    }
}
