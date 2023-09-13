import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public void greeting() {
        System.out.println("Hello! I'm Lakinta "  + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
}
