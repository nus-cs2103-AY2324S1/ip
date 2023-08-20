import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    private final Scanner scanner = new Scanner(System.in);
    public void horizontalLines() {
        System.out.println("____________________________________________________________"); //length taken from sample
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void displayActions(ArrayList<String> actions) {
        int queNumber = 1;
        for (String s : actions) {
            System.out.println(queNumber + ". " + s);
            queNumber++;
        }
    }
}
