import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(Exception e) {
        System.out.println("ERROR: " + e);
    }

    public void showMessage(String s) {
        System.out.println(s);
    }

}
