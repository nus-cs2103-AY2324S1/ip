import java.util.ArrayList;
import java.util.Scanner;
public class Ui {

    /**
     * Store the chat history
     */
    private ArrayList<String> history = new ArrayList<>();

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
        history.add(userInput); // Update History
        return userInput;
    }

    public void respond(String response) {
        history.add(response); // Update History
        System.out.println(response);
    }

    public String getLastMsg() {
        return history.get(history.size()-1);
    }
}
