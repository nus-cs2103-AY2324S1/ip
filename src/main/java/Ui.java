import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showGreeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Sara");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public void showFarewell() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + message);
        System.out.println("    ____________________________________________________________");
    }
}
