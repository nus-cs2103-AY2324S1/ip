import java.util.Scanner;
public class Ui {

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Ken");
        System.out.println("What can I do for you?");
        showLine();
    }
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String userInput = sc.nextLine();  // Read user input
        return userInput;
    }
}
