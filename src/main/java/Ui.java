import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Nobita");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
